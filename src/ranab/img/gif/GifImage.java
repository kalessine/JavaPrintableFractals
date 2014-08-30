package ranab.img.gif;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import ranab.img.*;

/**
 * GIF encoder class. It supports transparency, multiple images
 * and animation.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
class GifImage extends BaseImage {
    
    // GIF image mime type
    private static final String MIME_TYPE = "image/gif";
    
    // GIF header version
    static final String mstHeader = "GIF89a";

    // GIF LZW minimum code size
    private static final byte mbyMinCodeSize = 8; // root size

    // Actual data which has to be compressed 
    private BufferedImage mImageData;

    // GIF terminator
    private static final byte mbyTerminator = (byte)0x3B; //';'

    // GIF screen descriptor
    GifScreenDescriptor mScreenDesc;

    // GIF global color table
    GifColor mGlobalColTable[];

    // Application block (optional)
    GifNsAppEx mApplBlk;

    // Comment block (optional)
    GifCommentEx mComment;

    // Graphic control block (optional)
    GifGraphicEx mGraphicExt;

    // GIF image descriptor
    GifImageDescriptor  mImageDesc;

    // Other image data for animation or multiple image
    Vector mOtherImages;

    // temporary variables
    private GifHash mGifHash; 
    private byte[] mbyCodeBuffer = new byte[256+3];
    private short msBitOffset;
    private short msClearCode;
    private short msEofCode;
    private short msCodeSize;
    private short msMaxCode;
    private short msFreeCode;

    private Graphics2D    mGr = null;
    
    
    /**
     * Constructor
     */
    public GifImage(int width, int height) {
        super(width, height);
        mImageData = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);
        mGr = mImageData.createGraphics();
        
        mOtherImages = new Vector();
        
        // initialize gif blocks
        mScreenDesc = new GifScreenDescriptor((short)height, (short)width);
        mApplBlk  = new GifNsAppEx(mImageData);
        mComment  = new GifCommentEx(mImageData);
        mGraphicExt = new GifGraphicEx(mImageData);
        mImageDesc  = new GifImageDescriptor((short)height, (short)width);
        mGifHash = new GifHash();

        // initialize global color table
        mGlobalColTable = new GifColor[256];
        IndexColorModel cm = (IndexColorModel)mImageData.getColorModel();
        for (short s=0; s<mGlobalColTable.length; s++) {
            mGlobalColTable[s] = new GifColor(cm, s);
        }        
    }
    
    
    //////////////////////////////////////////////////////////////
    ///////////////////    All Get Methods   /////////////////////
    //////////////////////////////////////////////////////////////
    /**
     * Get graphics
     */
    public Graphics2D getGraphics() {
        return mGr;
    }
    
    /**
     * get mime type
     */
    public String getMimeType() {
        return MIME_TYPE;
    }
    
    /**
     * Get the gif comment block. You can change the default 
     * comment using this block.
     */
    public GifCommentEx getCommentBlock() {
        return mComment;
    }
     
    /**
     * Get graphic extension block. Image transparency, animation 
     * delay is controlled by this block.
     */ 
    public GifGraphicEx getGraphicBlock() {
        return mGraphicExt;
    } 
    
    /**
     * Get Netscape application block.
     */
    public GifNsAppEx getNsApplicationBlock() {
        return mApplBlk;
    }
     
    
    //////////////////////////////////////////////////////////////
    ///////////////////////   All Set Methods   //////////////////
    //////////////////////////////////////////////////////////////
    /**
     * Set transparency.
     */
    public void setTransparency(Color col) {
        getGraphicBlock().setTransparency(col);
    }

    /**
     * Reset transparency
     */
    public void resetTransparency() {
        getGraphicBlock().setTransparency(null);
    }

    /**
     * Set delay.
     */
    public void setDelay(int delay) {
        getGraphicBlock().setDelay(delay);
    }

    /**
     * Reset delay.
     */
    public void resetDelay() {
        getGraphicBlock().resetDelay();
    }


    /**
     * set iteration count
     */
    public void setIterationCount(int count) {
        getNsApplicationBlock().setIterationCount(count);
    }
    

    /**
     * Update color table
     */
    public void updateColorTable(Color col) {
        int rgb[] = new int[3];
        rgb[0] = col.getRed();
        rgb[1] = col.getGreen();
        rgb[2] = col.getBlue();

        IndexColorModel cm = (IndexColorModel)mImageData.getColorModel();
        int idx = cm.getDataElement(rgb, 0);

        mGlobalColTable[idx].updateColor(col);      
    }
    
    
    /**
     * print the global color table (debug only)
     */
    public void printColor() {
        int sz = mGlobalColTable.length;
        for (int i=0; i<sz; i++) {
            System.out.println(i + "> " + mGlobalColTable[i].getColor());
        }
    }


    /**
     * Add image - animation
     */
    public void addImage(GifImage gi) {
        mOtherImages.add(gi);
    } 


    /**
     * Encode GIF image
     */
    public void encode(OutputStream os) throws IOException {
        
        DataOutputStream ds = new DataOutputStream(os);

        // send header
        for (int i=0; i<mstHeader.length(); i++)
            ds.writeByte(mstHeader.charAt(i));

        // send screen descriptor
        mScreenDesc.write(ds);

        // write global color table
        for (int i=0; i<mGlobalColTable.length; i++) {
            mGlobalColTable[i].write(ds);
        }

        // netscape block
        mApplBlk.write(ds);

        // write comment
        mComment.write(ds);

        // write compressed image data
        writeImage(ds);


        // write other images
        short height = (short)getHeight();
        short width = (short)getWidth();

        for (int i=0; i<mOtherImages.size(); i++) {
            GifImage gu = (GifImage)mOtherImages.elementAt(i);
            short iheight = (short)gu.getHeight();
            short iwidth = (short)gu.getWidth();

            if (iheight > height || iwidth > width) {
                continue;
            }
            gu.writeImage(ds);
        }

        // write gif terminator
        ds.writeByte(mbyTerminator);

        ds.close();
        ds = null;
    }

    /**
     * Compress the image data (2D array) and send it. 
     * It may throw Exception.
     */
    private void writeImage(DataOutputStream os) throws IOException {

        // write graphic extension
        mGraphicExt.write(os);

        // write image descriptor
        mImageDesc.write(os);

        msBitOffset = 0;

        // reset temporary variable
        reset();

        // write minimum code size
        os.writeByte(mbyMinCodeSize);

        // send clear code
        writeCode(os, msClearCode);

        // get height and width
        short height = (short)mImageData.getHeight();
        short width = (short)mImageData.getWidth();

        // read each byte and compress
        Raster ras = mImageData.getData();
        int data[] = new int[1];

        ras.getPixel(0, 0, data);
        short prefixCode = (short)data[0];
        byte suffixChar = (byte)prefixCode;

        CodeEntry hashCode = null;
        for (int yidx=0; yidx<height; yidx++) {
            for (int xidx = (yidx==0)? 1 : 0; xidx<width; xidx++) {

                ras.getPixel(xidx, yidx, data);         
                suffixChar = (byte)data[0];

                // string table search
                int hx = mGifHash.findMatch(prefixCode, suffixChar);
                if (hx < -1)
                    throw new IOException("Hashing error");

                // match found in the string table
                hashCode = mGifHash.getCodeEntry(hx);
                if (!hashCode.isFree()) {
                    prefixCode = hashCode.getCode();
                } else {
                    writeCode(os, prefixCode);
                    int d = msFreeCode;

                    // table size is within limit
                    if (msFreeCode <= GifHash.MAX_CODE) {
                        hashCode.set(prefixCode, msFreeCode, suffixChar);       
                        msFreeCode++;
                    }

                    if (d == msMaxCode) {

                        // increase code size
                        if (msCodeSize < 12) {
                            msCodeSize++;
                            msMaxCode = (short)(msMaxCode*2);
                        } else {
                            writeCode(os, msClearCode);
                            reset();
                        }   
                    }
                    prefixCode = (short)(suffixChar & 0xff);
                }
            }
        }

        writeCode(os, prefixCode);
        writeCode(os, msEofCode);

        // flush the code buffer
        if (msBitOffset > 0) {
            flush(os, (msBitOffset+7)/8);
        }

        flush(os, 0);
    }


    /**
     * write code
     */
    private void writeCode(DataOutputStream ds, short code) 
    throws IOException {

        int temp;
        short byteOffset = (short)(msBitOffset >>> 3); // msBitOffset / 8
        short bitsLeft = (short)(msBitOffset & 0x07);  // msBitOffset % 8

        // send the block
        if (byteOffset >= 254) {
            flush(ds, byteOffset);
            mbyCodeBuffer[0] = mbyCodeBuffer[byteOffset];
            msBitOffset = bitsLeft;
            byteOffset = 0;
        }

        if (bitsLeft > 0) {
            temp = (code << bitsLeft) | mbyCodeBuffer[byteOffset];
            mbyCodeBuffer[byteOffset] = (byte)temp;
            mbyCodeBuffer[byteOffset+1] = (byte)(temp >> 8);
            mbyCodeBuffer[byteOffset+2] = (byte)(temp >> 16);
        } else {
            mbyCodeBuffer[byteOffset] = (byte)code;
            mbyCodeBuffer[byteOffset+1] = (byte)(code >> 8);
        }

        msBitOffset += msCodeSize;
    }


    /**
     * Flush the code buffer
     */
    private void flush(DataOutputStream ds, int n)
    throws IOException {

        ds.writeByte(n);

        for (int i=0; i<n; i++) {
            ds.writeByte(mbyCodeBuffer[i]);
        }
    }


    /**
     * Reset temporary variables
     */
    private void reset() {
        msClearCode = (short)(1 << mbyMinCodeSize); // 256
        msEofCode = (short)(msClearCode + 1);       // 257
        msFreeCode = (short)(msClearCode + 2);      // 258
        msCodeSize = (short)(mbyMinCodeSize + 1);   // 9
        msMaxCode = (short)(1 << msCodeSize);       // 512
        mGifHash.reset();
    }
 
    
    ///////////////////////////////////////////
    //             inner classes             //
    ///////////////////////////////////////////
    /**
     * code entry hashtable
     */
    final class GifHash {

        private static final int BITS = 12;                  //maximum bits/code
        private static final int MAX_CODE = (1 << BITS) - 1; //maximum code value (4095)
        private static final int TABLE_SIZE = 5021;          //table size - prime number

        private CodeEntry mHashTable[];

        /**
         * Initialize the array
         */
        public GifHash() {
            mHashTable = new CodeEntry[TABLE_SIZE];
            for (int i=0; i<TABLE_SIZE; i++) {
                mHashTable[i] = new CodeEntry();
            }
        }

        /**
         * Reset the array
         */
        public void reset() {
            for (int i=0; i<TABLE_SIZE; i++) {
                mHashTable[i].reset();
            }
        }

        /**
         * Hashing routine. It tries to find a match for the
         * prefix + char string in the string table. If found, 
         * returns the index, else returns the first available index.
         */
        public int findMatch(short codePrefix, byte charValue) {

            // hash function
            int hx = ((charValue << 5) ^ codePrefix) % TABLE_SIZE;
            if (hx < 0) {
                hx += TABLE_SIZE;
            }

            int loopCount = 0;
            while (true) {

                // check entry
                if (mHashTable[hx].isMatch(codePrefix, charValue)) {
                    return hx;
                }

                // increment count and check for excessive looping
                loopCount++;
                if ((++loopCount) >= TABLE_SIZE) {
                    return -1;
                }

                // rehash
                hx = (hx + loopCount) % TABLE_SIZE;
            }

        }


        /**
         * Get code entry object
         */
        public CodeEntry getCodeEntry(int index) {
            return mHashTable[index];
        }

    } 


    /**
     * Hash entry class
     */
    final class CodeEntry {

        private short msPriorCode;
        private short msCode;
        private byte  mbyAddedChar; 

        /**
         * Constructor
         */
        public CodeEntry() {
            msPriorCode = msCode = -1;
            mbyAddedChar = 0;
        }

        /**
         * Is it a free entry?
         */
        public boolean isFree() {
            return msCode == -1;
        }

        /**
         * Is it free or a match
         */
        public boolean isMatch(short pcode, byte achar) {
            return(msCode == -1) || 
            ((msPriorCode == pcode) && (mbyAddedChar == achar));
        }

        /**
         * Reset entry
         */
        public void reset() {
            msPriorCode = msCode = -1;
            mbyAddedChar = 0;
        }

        /**
         * Set the values
         */
        public void set(short pcode, short code, byte achar) {
            msPriorCode = pcode;
            msCode = code;
            mbyAddedChar = achar;
        }

        /**
         * Get entry code
         */
        public short getCode() {
            return msCode;
        }
    } 
    
}