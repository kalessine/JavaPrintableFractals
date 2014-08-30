/*
 * RenderListener.java
 *
 * Created on 9 February 2007, 10:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.render;

import fractal.producer.result.PixelValue;
import java.awt.image.BufferedImage;

/**
 *
 * @author Owner
 */
public interface RenderListener {
    public void renderStarted();
    public void renderFinished();
    public void updatePixel(int x, int y, PixelValue pixel);
    public void updateWholeView(BufferedImage bi);
}
