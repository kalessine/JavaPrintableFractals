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
public class ZSquaredPlusCFlip implements Formula {
    MathContext context = MathContext.DECIMAL64;
    double radius = 4d;
    
    public ZSquaredPlusCFlip() {
    }
    public void init(HashMap map,ComplexNumberFactory factory){
        ComplexNumber pixel = (ComplexNumber)map.get(Formula.PIXEL);
        map.put("z",pixel);
        map.put("c",pixel);
    }
    public void loop(HashMap map){
        ComplexNumber z = (ComplexNumber)map.get("z");
        ComplexNumber c = (ComplexNumber)map.get("c");
        ComplexNumber _z = z;
        z=z.sqr();
        c =c.flip();
        z=z.add(c);
        map.put("c",c);
        //z=z.add(_z.getImag().doubleValue() );        
        map.put("z",z);
    }
    public boolean bailout(HashMap map) {
        ComplexNumber z = (ComplexNumber)map.get("z");
        if( M.compareTo(z.getModSquared(),radius) >=0 ) return false;
        else return true;
    }
    public ZSquaredPlusCFlip(MathContext context) {
        this.context=context;
    }
    public Coordinates getDefaultCoordinates(){
        return Coordinates.createCoordinates(-3d,-2d,4d,4d,context);
    }
    public double getRadius() { return radius; }
    public void setRadius(double r) { this.radius=r; }
    public Formula clone() {
        ZSquaredPlusCFlip m = new ZSquaredPlusCFlip();
        m.setRadius(radius);
        return m;
    }
    public String toString() {
        return "Z^2+c(flip)";
    }
}
