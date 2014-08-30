package ranab.img.gif;

import java.io.*;
import java.awt.image.*;
import ranab.util.*;

/**
 * Gif Netscape application extension.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
final class GifNsAppEx extends GifAppEx {
    protected byte mbySubBlkSz = 0x03;
    protected byte mbyByte    = 0x01;   
    protected short msItrCnt  = 0x00;


    /**
     * Constructor
     */
    GifNsAppEx(BufferedImage imageData) {
        super(imageData);
        String ns = "NETSCAPE";
        for (int i=0; i<mbyAppId.length; i++) {
            mbyAppId[i] = (byte)ns.charAt(i);
        }

        String var = "2.0";
        for (int i=0; i<mbyAppAuth.length; i++) {
            mbyAppAuth[i] = (byte)var.charAt(i);
        }
    }


    /**
     * Set iteration count. Iteration count 0 means infinite
     */
    public void setIterationCount(int itr) {
        msItrCnt = (short)itr;
    }

    /**
     * Get iteration count
     */
    public int getIterationCount() {
        return msItrCnt & 0xffff;
    }


    /**
     * Write the block
     */
    public void write(DataOutputStream ds) throws IOException {
        ds.writeByte(mbyExIntro);
        ds.writeByte(mbyCtrlLab);
        ds.writeByte(mbyBlkSize);

        for (int i=0; i<mbyAppId.length; i++) {
            ds.writeByte(mbyAppId[i]);
        }

        for (int i=0; i<mbyAppAuth.length; i++) {
            ds.writeByte(mbyAppAuth[i]);
        }

        ds.writeByte(mbySubBlkSz);
        ds.writeByte(mbyByte);
        ds.writeShort(ByteUtils.changeSequence(msItrCnt));
        ds.writeByte(mbyBlkTerm);
    }

}