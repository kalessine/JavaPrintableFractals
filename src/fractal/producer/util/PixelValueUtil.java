/*
 * PixelValueUtil.java
 *
 * Created on 13 July 2007, 19:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.util;

import fractal.producer.exception.RenderException;
import fractal.producer.result.PixelValue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Owner
 */
public class PixelValueUtil {
    
    /** Creates a new instance of PixelValueUtil */
    public PixelValueUtil() {
    }
    public static BufferedImage createImage(PixelValue[][] pixels) {
        BufferedImage bi=null;
        try{
            bi = new BufferedImage(pixels[0].length,pixels.length,BufferedImage.TYPE_INT_RGB);
        }catch(java.lang.OutOfMemoryError oome) {
            throw new RuntimeException("Out of Memory - Sorry!!");
        }
        Graphics g = bi.getGraphics();
        for(int y=0; y<pixels.length;y++) {
            for(int x=0;x<pixels[y].length;x++){
                g.setColor(pixels[y][x].getColour());
                g.drawLine(x,y,x,y);
            }
        }
        return bi;
 
    }
}
