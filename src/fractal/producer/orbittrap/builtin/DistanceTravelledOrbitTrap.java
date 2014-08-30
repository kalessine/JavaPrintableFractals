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
 * adapted from Stephen Ferguson's render03.java from EmberJ
 * @author Owner
 */
public class DistanceTravelledOrbitTrap implements OrbitTrap {
    
    /** Creates a new instance of SimpleOrbitTrap */
    public DistanceTravelledOrbitTrap() {
    }
   
    boolean trapped = false;
    double distance = 0d;
    public void init(HashMap map){
        distance=0d;
    }
    ColourBlend blend = ColourBlend.SOLID;
    public void hit(HashMap map,Number iterations){
        ComplexNumber last = (ComplexNumber)map.get(Formula.LASTZ);
        ComplexNumber z = (ComplexNumber)map.get(Formula.Z);
        ComplexNumber delta = z.subtr(last);
        double x = delta.getReal().doubleValue();
        double y = delta.getImag().doubleValue();
        distance+=Math.sqrt(x*x+y*y);
    }
    public Object getTrapValue(){
        return distance;
    }
    public ColourBlend getBlend() { return blend; }
    public void setBlend(ColourBlend c) { this.blend=c; }
    double blendFactor = 0.5d;
    public double getBlendFactor() { return blendFactor; }
    public void setBlendFactor(double d) { blendFactor=d; }
    public Color getColour(PixelValue pv,ColourMap cmap,Color oldColour,HashMap map) {
        Result res = pv.getResult();
        Integer orbitindex = (Integer)map.get(OrbitTrap.ORBIT_INDEX);
        Double trap = (Double)res.getTrapValue(orbitindex.intValue());
        Color c = cmap.getColour(trap);
        return blend.blend(oldColour,c);
        //System.out.println(a.toString());
        //Color c2 = cmap.getColour( a.getMagnitude().doubleValue());
        //Color c = c1;
    }
    public Colouring clone() {
        return new DistanceTravelledOrbitTrap();
    }
    public String toString() { return "Distance Travalled Orbit Trap"; }
}