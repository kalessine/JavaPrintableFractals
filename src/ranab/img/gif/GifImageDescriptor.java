package ranab.img.gif;

import java.io.*;
import ranab.util.*;

/**
 * GIF image descriptor data structure.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
final class GifImageDescriptor {

    private final static byte mbySeparator = (byte)0x2C;  //','
    private final static short msLeft = 0;
    private final static short msTop = 0;
    private short msWidth;
    private short msHeight;
    final static byte mbyPackedField = 0;

    /**
     * Constructor
     */
    GifImageDescriptor(short height, short width) {
        msWidth = width;
        msHeight = height;
    }

    /**
     * Write image descriptor
     */
    public void write(DataOutputStream ds) throws IOException {
        ds.writeByte(mbySeparator);
        ds.writeShort(ByteUtils.changeSequence(msLeft));
        ds.writeShort(ByteUtils.changeSequence(msTop));
        ds.writeShort(ByteUtils.changeSequence(msWidth));
        ds.writeShort(ByteUtils.changeSequence(msHeight));
        ds.writeByte(mbyPackedField);
    }   
}