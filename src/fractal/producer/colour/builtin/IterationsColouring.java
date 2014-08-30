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
public class IterationsColouring extends AbstractColouring {
    
    /** Creates a new instance of IterationDirectColouring */
    public IterationsColouring() {
    }
    public Color getColour(PixelValue pv,ColourMap cmap){
        Result res = pv.getResult();
         return cmap.getColour(res.getIterations());
    }
    public Colouring clone() {
       return new IterationsColouring();
    }
    public String toString() {
       return "Iteration Colouring";
    }
}
