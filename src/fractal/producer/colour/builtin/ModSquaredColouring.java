/*
 * RealColouring.java
 *
 * Created on 5 March 2007, 16:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.colour.builtin;

import fractal.producer.colour.ColourMap;
import fractal.producer.colour.Colouring;
import fractal.producer.result.PixelValue;
import fractal.producer.result.Result;
import java.awt.Color;

/**
 *
 * @author Owner
 */
public class ModSquaredColouring extends AbstractColouring {
    
    /** Creates a new instance of RealColouring */
    public ModSquaredColouring() {
    }
    public Color getColour(PixelValue pv,ColourMap cmap){
        Result res = pv.getResult();
         Number n = res.getZ().getModSquared();
         return cmap.getColour(n.doubleValue()*factor);
    }
    public Colouring clone() {
       return new ModSquaredColouring();
    }
    public String toString() {
       return "Mod Squared";
    }
    double factor = 200d;
    public double getFactor() { return factor; }
    public void setFactor(double d) { factor=d; }
}
