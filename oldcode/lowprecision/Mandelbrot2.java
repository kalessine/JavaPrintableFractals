/*
 * Mandelbrot.java
 *
 * Created on November 7, 2006, 12:04 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.lowprecision;
import fractal.producer.*;
import fractal.producer.calc.*;
/**
 *
 * @author james
 */
public class Mandelbrot2 extends Producer {
    
    double radius = 4d;
    int maxIteration = 250;
    /** Creates a new instance of Mandelbrot */
    public Mandelbrot2() {
    }
    // An Unrolled mandelbrot at point algorithm.
    public int getColorAtPoint(double x, double y) {
       int iteration = 0;
       double r=x,i=y;
       double r2 = r * r;
       double i2 = i * i;
       while((r2+i2)<radius && iteration<maxIteration){
           i=(2*r2);
           r=(r2-i2);
           r+=x;
           i+=y;
           r2 = r * r;
           i2 = i * i;
           iteration++;
       }
       return iteration;
    }

    /**
     * Getter for property radius.
     * @return Value of property radius.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Setter for property radius.
     * @param radius New value of property radius.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Getter for property maxIteration.
     * @return Value of property maxIteration.
     */
    public int getMaxIteration() {
        return this.maxIteration;
    }

    /**
     * Setter for property maxIteration.
     * @param maxIteration New value of property maxIteration.
     */
    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }
}
