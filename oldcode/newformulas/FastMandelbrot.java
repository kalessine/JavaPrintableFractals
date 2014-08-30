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
public class FastMandelbrot extends Formula {
    final static BigDecimal PLUS500 = new BigDecimal(500);
    final static BigDecimal MINUS500 = new BigDecimal(-500);    
    double radius = 4d;
    int maxIteration = 249;
    /** Creates a new instance of Mandelbrot */
    public FastMandelbrot() {
    }
    
    public int getColorAtPoint(BigDecimal x,BigDecimal y,MathContext context) {
       if( context == MathContext.DECIMAL64)
          return Hardware.getFastMandelbrot(x.doubleValue(),y.doubleValue(),maxIteration,radius);
	   int count = 0;
	   BigDecimal zx = x;
	   BigDecimal zy = y;
	   while (count < maxIteration
	               && zx.compareTo(PLUS500)!=1 && zx.compareTo(MINUS500)!=-1
                   && zy.compareTo(PLUS500)!=1 && zy.compareTo(MINUS500)!=-1) {
	      BigDecimal new_zx = zx.multiply(zx).subtract(zy.multiply(zy)).add(x);
	      zy = BigMath.TWO.multiply(zx).multiply(zy).add(y);
	      zx = new_zx;
	      count++;
	   }
	   return count;    	
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
