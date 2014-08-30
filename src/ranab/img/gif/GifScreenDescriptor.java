package ranab.img.gif;

import java.io.*;
import ranab.util.*;

/**
 * GIF screen descriptor data structure.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
final class GifScreenDescriptor {

    private short msWidth;
    private short msHeight;
    private final static byte mbyPackedField = (byte)0xF7; //1111 0111
    private byte  mbyBackgroundIndex;
    private final static byte mbyAspectRatio = 0;


    /**
     * Constructor
     */
    GifScreenDescriptor(short height, short width) {
        msWidth = width;
        msHeight = height;
        mbyBackgroundIndex = (byte)20;
    }

    /**
     * write screen descriptor
     */
    public void write(DataOutputStream ds) throws IOException {
        ds.writeShort(ByteUtils.changeSequence(msWidth));
        ds.writeShort(ByteUtils.changeSequence(msHeight));
        ds.writeByte(mbyPackedField);
        ds.writeByte(mbyBackgroundIndex);
        ds.writeByte(mbyAspectRatio);
    }

    /**
     * Set background
     */
    public void setBackground(byte b) {
        mbyBackgroundIndex = b;
    }


    /**
     * Get image height
     */
    short getHeight() {
        return msHeight;
    }

    /**
     * get image width
     */
    short getWidth() {
        return msWidth;
    }
}