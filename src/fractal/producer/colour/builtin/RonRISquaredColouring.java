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
public class RonRISquaredColouring extends AbstractColouring {
    
    /** Creates a new instance of RealColouring */
    public RonRISquaredColouring() {
    }
    public Color getColour(PixelValue pv,ColourMap cmap){
        Result res = pv.getResult();
        double r = res.getZ().getReal().doubleValue();
        double i = res.getZ().getImag().doubleValue();
        r=r/r;
        i=i*i;
        return cmap.getColour(Math.max(r,i)-Math.min(r,i));
    }
    public Colouring clone() {
       return new RonRISquaredColouring();
    }
    public String toString() {
       return "RonR I^2 Colouring";
    }
}
