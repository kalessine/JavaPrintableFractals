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
import fractal.producer.colour.ColorUtil;
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
public class OrbitCountingOrbitTrap implements OrbitTrap {
    
    /** Creates a new instance of SimpleOrbitTrap */
    public OrbitCountingOrbitTrap() {
    }
    
    double angle = 0d;
    double opposite=180d;
    double lastAngle = 0d;
    
    int orbits = 0;
    boolean halfWay = false;
    public void init(HashMap map){
        orbits=0;
    }
    ColourBlend blend = ColourBlend.SOLID;
    
    public void hit(HashMap map,Number iterations){
        ComplexNumber z = (ComplexNumber)map.get(Formula.Z);
        if( M.compareTo(iterations,0)==0){
            angle=Math.atan2(z.getImag().doubleValue(),z.getReal().doubleValue());
            lastAngle=angle;
            opposite=(angle+180d)%360d;
            return;
        }
        // z = z.subtr(last);
        double a = Math.atan2(z.getImag().doubleValue(),z.getReal().doubleValue());
        double delta = lastAngle-a;
        lastAngle=a;
        double c = compareAngles(a,angle);
        if( c < 0 ) {
            if( halfWay ) {
               orbits++;
            }
            halfWay=false;
        }
        if( c > 0 ) { 
            if(!halfWay) {
               // Do Nothing
            }
            halfWay=true;
         }
    }
    
    public Object getTrapValue(){
        return orbits;
    }
    public ColourBlend getBlend() { return blend; }
    public void setBlend(ColourBlend c) { this.blend=c; }
    double blendFactor = 0.5d;
    public double getBlendFactor() { return blendFactor; }
    public void setBlendFactor(double d) { blendFactor=d; }
    public Color getColour(PixelValue pv,ColourMap cmap,Color oldColour,HashMap map) {
        Result res = pv.getResult();
        Integer orbitindex = (Integer)map.get(OrbitTrap.ORBIT_INDEX);
        Integer o = (Integer)res.getTrapValue(orbitindex.intValue());
        //System.out.println("av="+r);
        if( o==null)return oldColour;
        //System.out.println(a.toString());
        if( o > lowOrbit && o < highOrbit) {
            Color c = cmap.getColour( o );
            switch(blend.toInt()){
                case ColourBlend.BLEND_SOLID:
                    return c;
                case ColourBlend.BLEND_QUARTER:
                    return ColorUtil.blend(oldColour,c,0.75d);
                case ColourBlend.BLEND_HALF:
                    return ColorUtil.blend(oldColour,c,0.5d);
                case ColourBlend.BLEND_THREEQUARTER:
                    return ColorUtil.blend(oldColour,c,0.25d);
                case ColourBlend.BLEND_FACTOR:
                    return ColorUtil.blend(oldColour,c,1d-blendFactor);
                default:return c;
            }
        } else return oldColour;
        
    }
    int lowOrbit = 0;
    int highOrbit = 3000;
    public int getLowOrbits() { return lowOrbit; }
    public int getHighOrbits() { return highOrbit; }
    public void setLowOrbits(int d)  { lowOrbit=d;}
    public void setHighOrbits(int d) { highOrbit=d; }
    
    
    public Colouring clone() {
        return new OrbitCountingOrbitTrap();
    }
    public String toString() { return "Orbit Counting Orbit Trap"; }
    public double compareAngles(double a1, double a2) {
         a1=a1%360d;
         a2=a2%360d;
         return a1-a2;
    }
}