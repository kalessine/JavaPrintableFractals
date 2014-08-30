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
public class OneOverZNPlusC implements Formula {
    MathContext context = MathContext.DECIMAL64;
    double radius = 4d;
    ComplexNumber power = new ComplexDouble(2d,0d);
    public OneOverZNPlusC() {
    }
    public void init(HashMap map,ComplexNumberFactory factory){
        ComplexNumber pixel = (ComplexNumber)map.get(Formula.PIXEL);
        map.put("z",factory.createComplexNumber());
        map.put("c",pixel);
    }
    ComplexNumber one = new ComplexDouble(1d,0d);
    public void loop(HashMap map){
        ComplexNumber z = (ComplexNumber)map.get("z");
        ComplexNumber c = (ComplexNumber)map.get("c");
        z=z.pow(power).conjugate();
        z=z.add(c);
        map.put("z",z);
    }
    public boolean bailout(HashMap map) {
        ComplexNumber z = (ComplexNumber)map.get("z");
        if( Double.isNaN(z.getImag().doubleValue()) ||
            Double.isNaN(z.getReal().doubleValue()) ||
            M.compareTo(z.getModSquared(),radius) >=0 ) return false;
        else return true;
    }
    public OneOverZNPlusC(MathContext context) {
        this.context=context;
    }
    public Coordinates getDefaultCoordinates(){
        return Coordinates.createCoordinates(-3d,-2d,4d,4d,context);
    }
    public double getRadius() { return radius; }
    public void setRadius(double r) { this.radius=r; }
    public Formula clone() {
        OneOverZNPlusC cp = new OneOverZNPlusC();
        return cp;
    }
    public String toString() {
        return "(OneOverZ^N)+c";
    }
    public ComplexNumber getComplexPower() { return power; }
    public void setComplexPower(ComplexNumber c) { power=c; }
}
