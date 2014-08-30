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
public class Julia extends Producer {
    
    double radius = 8d;
    int maxIteration = 500;
    transient ComplexNumber constant = new ComplexNumber();
    transient ComplexNumber cplx = new ComplexNumber();
    /** Creates a new instance of Mandelbrot */
    public Julia(double x, double y) {
        constant.set(x,y);
    }
    
    
    public int getColorAtPoint(double x, double y) {
       ComplexNumber base = new ComplexNumber(x,y);
       cplx.set(x,y);
       int iteration = 0;
       while(cplx.modsq()<radius && iteration<maxIteration){
           cplx.sqr();
           cplx.add(constant);
           iteration++;
       }
       if(iteration>maxIteration){
           System.out.println("Max Iteration");
           iteration=maxIteration;
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
