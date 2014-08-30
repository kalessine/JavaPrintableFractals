package ranab.img.gif;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

/**
 * Color data structure.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
final class GifColor {

    private byte mbyRed;
    private byte mbyGreen;
    private byte mbyBlue;

    /**
     * Constructor
     */
    GifColor(IndexColorModel cm, int idx) {
        mbyRed   = (byte)cm.getRed(idx);
        mbyGreen = (byte)cm.getGreen(idx);
        mbyBlue  = (byte)cm.getBlue(idx);
    }

    /**
     * Write gif color
     */
    public void write(DataOutputStream ds) throws IOException {
        ds.writeByte(mbyRed);
        ds.writeByte(mbyGreen);
        ds.writeByte(mbyBlue);
    }

    /**
     * Update color entry
     */
    public void updateColor(Color col) {
        mbyRed = (byte)col.getRed();
        mbyGreen = (byte)col.getGreen();
        mbyBlue = (byte)col.getBlue();
    }


    /**
     * Get color
     */
    public Color getColor() {
        return new Color(mbyRed & 0x00ff, mbyGreen & 0xff, mbyBlue & 0xff);
    }
}