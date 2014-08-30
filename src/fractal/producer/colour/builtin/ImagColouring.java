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
public class ImagColouring extends AbstractColouring {
    
    /** Creates a new instance of RealColouring */
    public ImagColouring() {
    }
    public Color getColour(PixelValue pv,ColourMap cmap){
        Result res = pv.getResult();
         Number n = res.getZ().getImag();
         return cmap.getColour(n.doubleValue()*multiplier);
    }
    public Colouring clone() {
       return new ImagColouring();
    }
    public String toString() {
       return "Imag Colouring";
    }
    double multiplier=100d;
    public double getMultiplier() { return multiplier; }
    public void setMultiplier(double d) { multiplier = d ; }

}
