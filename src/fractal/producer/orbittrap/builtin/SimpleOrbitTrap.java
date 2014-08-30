/*
 * SimpleOrbitTrap.java
 *
 * Created on 7 March 2007, 21:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.orbittrap.builtin;

import fractal.producer.calc.ComplexNumber;
import fractal.producer.calc.M;
import fractal.producer.colour.ColourBlend;
import fractal.producer.colour.ColourMap;
import fractal.producer.colour.Colouring;
import fractal.producer.formula.Formula;
import fractal.producer.orbittrap.OrbitTrap;
import fractal.producer.result.PixelValue;
import fractal.producer.result.Result;
import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author Owner
 */
public class SimpleOrbitTrap implements OrbitTrap {
    
    /** Creates a new instance of SimpleOrbitTrap */
    public SimpleOrbitTrap() {
    }
    public void init(HashMap map){
        ComplexNumber c = (ComplexNumber)map.get(Formula.PIXEL);
        ztot=0d;
        cMagnitude=c.getMagnitude().doubleValue();
    }
    double cMagnitude = 0d;
    double ztot = 0d;
    double radiusHi = 1d;
    double radiusLo = 0.00d;
    double dRadius = 1d;

    ColourBlend blend = ColourBlend.SOLID;
    
    public void hit(HashMap map,Number iterations){
      ComplexNumber z = (ComplexNumber)map.get(Formula.Z);
      double zMagnitude = z.getMagnitude().doubleValue();
      if (zMagnitude < radiusHi && zMagnitude > radiusLo && M.compareTo(iterations,1)>0 ) 
      {
      ztot = Math.sqrt(ztot) + (1 - Math.abs(zMagnitude - cMagnitude) / dRadius); 
      }
    }
    public Object getTrapValue(){
        return ztot;
    }
    public ColourBlend getBlend() { return blend; }
    public void setBlend(ColourBlend c) { this.blend=blend; }
    public Color getColour(PixelValue pv,ColourMap cmap,Color oldColour,HashMap map) {
        Result res = pv.getResult();
        Integer orbitindex = (Integer)map.get(OrbitTrap.ORBIT_INDEX);
        double trap = ((Double)res.getTrapValue(orbitindex)).doubleValue();
        if (trap > 0)
           return cmap.getColour( (int)(trap*cmap.getMapSize()));
        else return oldColour;
    }
    public Colouring clone() {
        return new SimpleOrbitTrap();
    }
    public double getRadiusHi() { return radiusHi; }
    public void setRadiusHi( double d) { radiusHi=d; }
    public void setRadiusLo(double d) {radiusLo=d; }
    public double getRadiusLo() { return radiusLo; }
}
