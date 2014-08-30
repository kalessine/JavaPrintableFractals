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
public class AngleToOriginColouring extends AbstractColouring {
    
    /** Creates a new instance of RealColouring */
    public AngleToOriginColouring() {
    }
    public Color getColour(PixelValue pv,ColourMap cmap){
        Result res = pv.getResult();
        double angle = res.getZ().getAngle().doubleValue();
        //if( !(angle > 0.5 && angle < 0.7) ) return null;
        //return Color.BLUE;
        return cmap.getColour(angle*multiplier);
    }
    public Colouring clone() {
       return new AngleToOriginColouring();
    }
    public String toString() {
       return "Angle2Origin Colouring";
    }
    double multiplier = 100;
    public double getMultiplier() { return multiplier; }
    public void setMultiplier(double d) { multiplier=d; }
}
