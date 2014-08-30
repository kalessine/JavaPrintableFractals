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
/**
 *
 * @author james
 */
public class Hardware {
    
    private Hardware() {
    }
    public static int getMandelbrot(double x, double y,int maxIteration, double radius) {
       ComplexDouble base = new ComplexDouble(x,y);
       ComplexDouble cplx = new ComplexDouble(x,y);
       int iteration = 0;
       while(cplx.modsq()<radius && iteration<maxIteration){
           cplx.sqr();
           cplx.add(base);
           iteration++;
       }
       return iteration;
    }
    // 'Borrowed From 'math.hws.edu/xJava/MB/'
    public static int getFastMandelbrot(double x, double y, int maxIterations,double radius) {
	   int count = 0;
	   double zx = x;
	   double zy = y;
	   while (count < maxIterations
	               && zx < 500 && zx > -500 && zy < 500 && zy > -500) {
	      double new_zx = zx*zx - zy*zy + x;
	      zy = 2*zx*zy + y;
	      zx = new_zx;
	      count++;
	   }
	   return count;    	
    }
    public static int getJulia(double x, double y,int maxIteration, double radius,double juliax,double juliay) {
       ComplexDouble cplx = new ComplexDouble(x,y);
       int iteration = 0;
       while(cplx.modsq()<radius && iteration<maxIteration){
           cplx.sqr();
           cplx.add(juliax,juliay);
           iteration++;
       }
       return iteration;
    }
    
    public static int getCubicMandelbrot(double x, double y,int maxIteration, double radius) {
       ComplexDouble base = new ComplexDouble(x,y);
       ComplexDouble cplx = new ComplexDouble(x,y);
       int iteration = 0;
       while(cplx.modsq()<radius && iteration<maxIteration){
           cplx.cube();
           cplx.add(base);
           iteration++;
       }
       return iteration;
    }
    public static int getCubicJulia(double x, double y,int maxIteration, double radius,double juliax,double juliay) {
       ComplexDouble cplx = new ComplexDouble(x,y);
       int iteration = 0;
       while(cplx.modsq()<radius && iteration<maxIteration){
           cplx.cube();
           cplx.add(juliax,juliay);
           iteration++;
       }
       return iteration;
    }
    public static int getQuarticMandelbrot(double x, double y,int maxIteration, double radius) {
       ComplexDouble base = new ComplexDouble(x,y);
       ComplexDouble cplx = new ComplexDouble(x,y);
       int iteration = 0;
       while(cplx.modsq()<radius && iteration<maxIteration){
           cplx.sqr();
           cplx.sqr();
           cplx.add(base);
           iteration++;
       }
       return iteration;
    }
    public static int getSquareCubeMandelbrot(double x, double y,int maxIteration, double radius) {
       ComplexDouble base = new ComplexDouble(x,y);
       ComplexDouble cplx = new ComplexDouble(x,y);
       int iteration = 0;
       while(cplx.modsq()<radius && iteration<maxIteration){
           cplx.sqr();
           cplx.cube();
           cplx.add(base);
           iteration++;
       }
       return iteration;
    }

}
