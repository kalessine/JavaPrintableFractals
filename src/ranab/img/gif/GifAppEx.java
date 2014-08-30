package ranab.img.gif;

import java.awt.image.*;

/**
 * Gif application extension.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
abstract class GifAppEx extends GifEx {

    protected final static byte mbyBlkSize = (byte)0x0B;
    protected byte mbyAppId[] = new byte[8];
    protected byte mbyAppAuth[] = new byte[3];


    /**
     * Constructor
     */
    GifAppEx(BufferedImage imageData) {
        super(imageData);
        mbyCtrlLab = (byte)0xFF;
    }
}