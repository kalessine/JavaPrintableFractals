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
import fractal.producer.result.Result;
import java.awt.Color;

/**
 *
 * @author Owner
 */
public class AdjustableStripeColouring extends AbstractColouring {
    
    /** Creates a new instance of RealColouring */
    public AdjustableStripeColouring() {
    }
    public Color getColour(Result res,ColourMap cmap){
        double r = res.getZ().getReal().doubleValue();
        double i = res.getZ().getImag().doubleValue();
        double d = Math.sqrt(r*r+i*i);
        if( distance-(width/2d) < d && d < distance+(width/2d) ) {
           double ds = d - distance;
           ds = Math.abs(ds);
           ds = ds/width;
           ds = ds*(cmap.getMapSize()*2);
           return cmap.getColour(ds);    
        }
        return null;
    }
    public Colouring clone() {
       return new AdjustableStripeColouring();
    }
    public String toString() {
       return "Adjustable Stripe Colouring";
    }
    public double distance = 6.0;
    public void setDistance(double d) { distance=d; }
    public double getDistance(){ return distance; }
    public double width = 15d;
    public void setWidth(double d) { width =d; }
    public double getWidth() { return width; }
}
