/*
 * Mandelbrot.java
 *
 * Created on November 7, 2006, 12:04 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.formula.builtin;
import fractal.producer.PrecisionContext;
import fractal.producer.calc.*;
import fractal.producer.Coordinates;
import fractal.producer.formula.*;
import fractal.producer.result.Result;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;

/**
 *
 * @author james
 */
public class Evert implements Formula {
    MathContext context = MathContext.DECIMAL64;
    double radius = 0.5d;
    private ComplexNumber inversePoint = new ComplexDouble();
    
    public Evert() {
    }
    public void init(HashMap map,ComplexNumberFactory factory){
        map.put(Formula.Z,map.get(Formula.PIXEL));
    }
    public void loop(HashMap map){
        ComplexNumber pixel = (ComplexNumber)map.get(Formula.PIXEL);
        pixel=inversePoint.add(radius).divid(pixel.subtr(inversePoint));
        map.put(Formula.Z,pixel);
    }
    public boolean bailout(HashMap map) {
        return true;
    }
    public Evert(MathContext context) {
        this.context=context;
    }
    public Coordinates getDefaultCoordinates(){
        return Coordinates.createCoordinates(-3d,-2d,4d,4d,context);
    }
    public double getRadius() { return radius; }
    public void setRadius(double r) { this.radius=r; }
    public Formula clone() {
        Evert e = new Evert();
        e.setRadius(radius);
        return e;
    }
    public String toString() {
        return "Evert";
    }
    
    public ComplexNumber getInversePoint() {
        return inversePoint;
    }
    
    public void setInversePoint(ComplexNumber inversePoint) {
        this.inversePoint = inversePoint;
    }
}
