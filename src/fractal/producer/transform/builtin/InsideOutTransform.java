/*
 * InsideOutTransform.java
 *
 * Created on 27 February 2007, 18:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.transform.builtin;

import fractal.producer.calc.ComplexDouble;
import fractal.producer.calc.ComplexNumber;
import fractal.producer.transform.Transform;
import java.util.HashMap;

/**
 *
 * @author Owner
 */
public class InsideOutTransform implements Transform {
    
    ComplexNumber inversePoint = new ComplexDouble(0d,0d);
    double radius = 0.5d;
    boolean useScreenCenter = false;
    /** Creates a new instance of InsideOutTransform */
    public InsideOutTransform() {
    }
    
    public ComplexNumber transform(ComplexNumber point,HashMap map){
        if( useScreenCenter ) {
            ComplexNumber center = (ComplexNumber)map.get("center");
            inversePoint=center;
            useScreenCenter=false;
        }
        point=inversePoint.add(radius).divid(point.subtr(inversePoint));
        return point;
    }
    
    public boolean getUseScreenCenter() { return useScreenCenter; }
    public void setUseScreenCenter(boolean b) { useScreenCenter = b; }
    public ComplexNumber getInversePoint() { return inversePoint; }
    public void setInversePoint(ComplexNumber p) { inversePoint=p; }
    public Transform clone() {
        InsideOutTransform iot = new InsideOutTransform();
        iot.setUseScreenCenter(useScreenCenter);
        iot.setInversePoint(inversePoint);
        iot.setRadius(radius);
        return iot;
    }
    public String toString() { return "Inside Out"; }
    public double getRadius() { return radius; }
    public void setRadius(double d) { this.radius=d; }
}
