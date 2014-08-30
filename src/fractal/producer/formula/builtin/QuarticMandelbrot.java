/*
 * Mandelbrot.java
 *
 * Created on November 7, 2006, 12:04 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.formula.builtin;
import fractal.producer.calc.*;
import fractal.producer.Coordinates;
import fractal.producer.formula.*;
import java.math.MathContext;
import java.util.HashMap;

/**
 *
 * @author james
 */
public class QuarticMandelbrot implements Formula {
    MathContext context = MathContext.DECIMAL64;
    double radius = 4d;
    
    public QuarticMandelbrot() {
    }
    public void init(HashMap map,ComplexNumberFactory factory){
        ComplexNumber pixel = (ComplexNumber)map.get(Formula.PIXEL);
        map.put("z",factory.createComplexNumber());
        map.put("c",pixel);
    }
    public void loop(HashMap map){
        ComplexNumber z = (ComplexNumber)map.get("z");
        ComplexNumber c = (ComplexNumber)map.get("c");
        z=z.sqr();
        z=z.sqr();
        z=z.add(c);
        map.put("z",z);
    }
    public boolean bailout(HashMap map) {
        ComplexNumber z = (ComplexNumber)map.get("z");
        if( M.compareTo(z.getModSquared(),radius) >=0 ) return false;
        else return true;
    }
    public QuarticMandelbrot(MathContext context) {
        this.context=context;
    }
    public Coordinates getDefaultCoordinates(){
        return Coordinates.createCoordinates(-3d,-2d,4d,4d,context);
    }
    public double getRadius() { return radius; }
    public void setRadius(double r) { this.radius=r; }
    public Formula clone() {
        QuarticMandelbrot m = new QuarticMandelbrot();
        m.setRadius(radius);
        return m;
    }
    public String toString() {
        return "Quartic Mandelbrot";
    }
}
