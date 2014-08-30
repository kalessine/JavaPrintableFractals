package ranab.img.gif;

import java.io.*;
import java.awt.image.*;

/**
 * Comment extension block.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
final class GifCommentEx extends GifEx {

    private String mstComment = "Gif encoder by Rana Bhattacharyya (rana_b@yahoo.com).";

    /**
     * Constructor
     */
    GifCommentEx(BufferedImage imageData) {
        super(imageData);
        mbyCtrlLab = (byte)0xFE;
    }
    
    /**
     * Get the comment string.
     */
    public String getComment() {
        return mstComment;
    }
     
    /**
     * Set the comment. Set <code>null</code> to remove comment.
     */
    public synchronized void setComment(String comment) {
        mstComment = comment;
    }
     
    /**
     * Write comment this block. If the comment string is <code>null</code>
     * does not write.
     */
    public synchronized void write(DataOutputStream ds) throws IOException {
        if(mstComment != null) {
            ds.writeByte(mbyExIntro);
            ds.writeByte(mbyCtrlLab);
            int sz = mstComment.length();
            ds.writeByte(sz);

            for (int i=0; i<sz; i++) {
                ds.writeByte(mstComment.charAt(i)); 
            }
            ds.writeByte(mbyBlkTerm);
        }
    }
    
}