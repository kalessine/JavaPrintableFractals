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
public class JuliaComplexPower implements Formula {
    MathContext context = MathContext.DECIMAL64;
    double radius = 4d;
    private ComplexNumber point = new ComplexDouble(0d,0d);
    private ComplexNumber power = new ComplexDouble(2d,0d);
    public JuliaComplexPower() {
    }
    public void init(HashMap map,ComplexNumberFactory factory){
        ComplexNumber pixel = (ComplexNumber)map.get(Formula.PIXEL);
        map.put(Formula.Z,pixel);
//        map.put("c", getPoint());
    }
    public void loop(HashMap map){
        ComplexNumber z = (ComplexNumber)map.get(Formula.Z);
        z=z.pow(power);
        z=z.add(getPoint());
        map.put(Formula.Z,z);
    }
    public boolean bailout(HashMap map) {
        ComplexNumber z = (ComplexNumber)map.get(Formula.Z);
        if( M.compareTo(z.getModSquared(),radius) >=0 ) return false;
        else return true;
    }
    public JuliaComplexPower(MathContext context) {
        this.context=context;
    }
    public Coordinates getDefaultCoordinates(){
        return Coordinates.createCoordinates(-3d,-2d,4d,4d,context);
    }
    public double getRadius() { return radius; }
    public void setRadius(double r) { this.radius=r; }
    public Formula clone() {
        JuliaComplexPower m = new JuliaComplexPower();
        m.setRadius(radius);
        m.setPoint(point);
        m.setComplexPower(power);
        return m;
    }
    public String toString() {
        return "Julia Complex Power";
    }

    public ComplexNumber getPoint() {
        return point;
    }

    public void setPoint(ComplexNumber point) {
        this.point = point;
    }
    public ComplexNumber getComplexPower() {
        return power;
    }

    public void setComplexPower(ComplexNumber power) {
        this.power = power;
    }

}
