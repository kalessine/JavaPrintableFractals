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
public class InColouring extends AbstractColouring {
    
    /** Creates a new instance of IterationDirectColouring */
    public InColouring() {
        setTrapInColour(true);
        setTrapOutColour(false);
    }
    Color in = Color.BLACK;
    public Color getColour(PixelValue pv,ColourMap cmap){
        Result res = pv.getResult();
        if( res.isIn()){
            return in;}
        else return null;
    }
    public Colouring clone() {
       return new InColouring();
    }
    public String toString() {
       return "In Colouring";
    }
    public Color getInColor() { return in; }
    public void setInColor(Color c) { in=c; }
}
