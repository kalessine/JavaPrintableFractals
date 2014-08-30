/*
 * RealColouring.java
 *
 * Created on 5 March 2007, 16:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.colour.builtin;

import fractal.producer.calc.ComplexNumber;
import fractal.producer.colour.ColourMap;
import fractal.producer.colour.Colouring;
import fractal.producer.result.PixelValue;
import fractal.producer.result.Result;
import java.awt.Color;

/**
 *
 * @author Owner
 */
public class DeltaAngleColouring extends AbstractColouring {
    
    /** Creates a new instance of RealColouring */
    public DeltaAngleColouring() {
    }
    public Color getColour(PixelValue pv,ColourMap cmap){
         Result res = pv.getResult();
         ComplexNumber c = res.getZ().subtr(pv.getTransformed());
         return cmap.getColour(c.getAngle().doubleValue()*factor);
    }
    public Colouring clone() {
       return new DeltaAngleColouring();
    }
    public String toString() {
       return "Delta Angle";
    }
    double factor = 200d;
    public double getFactor() { return factor; }
    public void setFactor(double d) { factor=d; }
}
