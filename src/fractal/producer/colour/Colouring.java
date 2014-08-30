/*
 * Colouring.java
 *
 * Created on 4 March 2007, 22:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.colour;

import fractal.producer.result.PixelValue;
import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Owner
 */
public interface Colouring extends Serializable {
    public ColourBlend getBlend();
    public void setBlend(ColourBlend c);
    public abstract Color getColour(PixelValue pv,ColourMap cmap,Color oldColour,HashMap map);
//    public abstract Color getColour(Result res,ColourMap cmap);
    public abstract Colouring clone();
}
