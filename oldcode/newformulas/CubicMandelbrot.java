/*
 * Mandelbrot.java
 *
 * Created on November 7, 2006, 12:04 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.formula.old;
import fractal.producer.*;
import fractal.producer.calc.*;
import fractal.producer.formula.*;
import java.math.MathContext;
import java.math.BigDecimal;
/**
 *
 * @author james
 */
public class CubicMandelbrot extends Formula {
    
    double radius = 4d;
    int maxIteration = 249;
    /** Creates a new instance of Mandelbrot */
    public CubicMandelbrot() {
    }
    
    public int getColorAtPoint(BigDecimal x,BigDecimal y,MathContext context) {
       if( context == MathContext.DECIMAL64)
          return Hardware.getCubicMandelbrot(x.doubleValue(),y.doubleValue(),maxIteration,radius);
       ComplexArbitrary base = new ComplexArbitrary(x,y,context);
       ComplexArbitrary cplx = new ComplexArbitrary(context);
       int iteration = 0;
       BigDecimal bdradius=new BigDecimal(radius);
       while(BigMath.sqrt(cplx.modsq().abs()).compareTo(bdradius)==-1 && iteration<maxIteration){
           cplx.cube();
           cplx.add(base);
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
