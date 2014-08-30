package ranab.img.gif;

import java.io.*;
import java.awt.image.*;

/**
 * Abstract base class of all the GIF extension blocks.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
abstract class GifEx {
    
    protected final static byte mbyExIntro = (byte)0x21;
    protected final static byte  mbyBlkTerm   = 0x00;
    
    protected byte mbyCtrlLab = (byte)0x00; // unassigned
    protected BufferedImage mImageData;
    
    /**
     * Constructor - set the <code>BufferedImage</code>.
     * This constructor will be called by <code>GifImage</code>.
     */
    GifEx(BufferedImage imageData) {
        mImageData = imageData;
    }
    
    /** 
     * Get the block identification block.
     */
    public byte getBlockId() {
        return mbyCtrlLab;
    }
    
    /**
     * Get image data.
     */
    public BufferedImage getImageData() {
        return mImageData;
    }
     
    /**
     * Write this block.
     */
    abstract void write(DataOutputStream ds) throws IOException;
}