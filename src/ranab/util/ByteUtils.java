package ranab.util;

/**
 * Byte manipulation utility class.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
class ByteUtils {
    
    /**
     * Change LSB and MSB sequence.
     */
    public static short changeSequence(short b) {
        short msb = (short)(b >>> 8);
        short lsb = (short)(b & 0xFF);
        return(short)((lsb << 8) | msb);
    }
}    