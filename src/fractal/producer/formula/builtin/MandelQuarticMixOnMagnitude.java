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
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.math.MathContext;
import java.util.HashMap;

/**
 *
 * @author james
 */
public class MandelQuarticMixOnMagnitude implements Formula {
    MathContext context = MathContext.DECIMAL64;
    Formula leftForm = new Mandelbrot();
    Formula rightForm = new QuarticMandelbrot();
    public MandelQuarticMixOnMagnitude() {
    }
    public void init(HashMap map,ComplexNumberFactory factory){
        ComplexNumber pixel = (ComplexNumber)map.get(Formula.PIXEL);
        leftForm.init(map,factory);
        rightForm.init(map,factory);
    }
    public void loop(HashMap map){
        ComplexNumber z = (ComplexNumber)map.get("z");
        if( z.getMagnitude().doubleValue() > magnitude ) {
            leftForm.loop(map);
        }else rightForm.loop(map);
        
    }
    public boolean bailout(HashMap map) {
        return leftForm.bailout(map);
    }
    public MandelQuarticMixOnMagnitude(MathContext context) {
        this.context=context;
    }
    public Coordinates getDefaultCoordinates(){
        return Coordinates.createCoordinates(-3d,-2d,4d,4d,context);
    }
    public Formula clone() {
        MandelQuarticMixOnMagnitude f = new MandelQuarticMixOnMagnitude();
        return f;
    }
    public String toString() {
        return "Mandel Quartic Mix On Magnitude";
    }
    double magnitude = 1d;
    public void setMagnitude(double d) { this.magnitude=d; }
    public double getMagnitude() { return magnitude; }
}
