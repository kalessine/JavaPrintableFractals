/*
 * IterationDirectColouring.java
 *
 * Created on 4 March 2007, 23:05
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
public class LogTenIterationsColouring extends AbstractColouring {
    
    /** Creates a new instance of IterationDirectColouring */
    public LogTenIterationsColouring() {
    }
    public Color getColour(PixelValue pv,ColourMap cmap){
        Result res = pv.getResult();
        double Liter = Math.log10(res.getIterations().doubleValue());
        Liter=Liter*multiplier;
        return cmap.getColour(Liter);
    }
    public Colouring clone() {
       return new LogTenIterationsColouring();
    }
    public String toString() {
       return "Log Ten Iteration Colouring";
    }
    double multiplier = 100.0d;
    public double getMultiplier() { return multiplier; }
    public void setMultiplier(double d) { multiplier=d; }
}
