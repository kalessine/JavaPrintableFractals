/*
 * SimpleOrbitTrap.java
 *
 * Created on 7 March 2007, 21:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.orbittrap.builtin;

import fractal.producer.calc.ComplexDouble;
import fractal.producer.calc.ComplexNumber;
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
 * adapted from Stephen Ferguson's render03.java from EmberJ
 * @author Owner
 */
public class StephenOrbitTrap1b_1 implements OrbitTrap {
    
    /** Creates a new instance of SimpleOrbitTrap */
    public StephenOrbitTrap1b_1() {
    }
    double zsqr0 = 0d;
    double xtot = 1;
    double ytot=1;
    boolean finished = false;
    int iter = 0;
    public void init(HashMap map){
        finished=false;
        xtot=1;
        ytot=1;
    }
    ColourBlend blend = ColourBlend.SOLID;
    
    public void hit(HashMap map,Number iterations){
        if(finished)return;
        iter=iterations.intValue();
        if( iterations.intValue()==0 ) {
            zsqr0=((ComplexNumber)map.get(Formula.Z)).getModSquared().doubleValue();
        }
        
        ComplexNumber z = (ComplexNumber)map.get(Formula.Z);
        ComplexNumber lastz = (ComplexNumber)map.get(Formula.LASTZ);
        double delta = Math.abs(z.getModSquared().doubleValue()-zsqr0);
        delta *= delta;
        double zsqrX = z.getReal().doubleValue() * z.getReal().doubleValue();
        double zsqrY = z.getImag().doubleValue() * z.getImag().doubleValue();
        double temp = Math.sqrt(z.getModSquared().doubleValue() - zsqr0);
        xtot += Math.abs(zsqrX+Math.sin(zsqrX*10));
        ytot += Math.abs(zsqrY+Math.sin(zsqrY*10));
        if ( (delta < zerotol) || (delta > overflow))
            //if (( delta < m.zerotol) || (delta > 1))
            finished=true;
        else
            finished=false;
    }
    
    public Object getTrapValue(){
        return new ComplexDouble(xtot/(iter/100d),ytot/(iter/100d));
    }
    public ColourBlend getBlend() { return blend; }
    public void setBlend(ColourBlend c) { this.blend=c; }
    double blendFactor = 0.5d;
    public double getBlendFactor() { return blendFactor; }
    public void setBlendFactor(double d) { blendFactor=d; }
    public Color getColour(PixelValue pv,ColourMap cmap,Color oldColour,HashMap map) {
        Result res = pv.getResult();
        Integer orbitindex = (Integer)map.get(OrbitTrap.ORBIT_INDEX);
        ComplexDouble a = (ComplexDouble)res.getTrapValue(orbitindex.intValue());
        //System.out.println("av="+r);
        if( a==null)return oldColour;
        //System.out.println(a.toString());
        Color c1 = cmap.getColour( a.getReal().doubleValue());
        Color c2 = cmap.getColour(a.getImag().doubleValue());
        //Color c2 = cmap.getColour( a.getMagnitude().doubleValue());
        Color c = ColorUtil.blend(c1,c2);
        //Color c = c1;
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
    }
    double zerotol = 0d;
    double overflow = 30d;
    public double getZeroTolerance() { return zerotol; }
    public double getOverflow() { return overflow; }
    public void setZeroTolerance(double d)  { zerotol=d;}
    public void setOverflow(double d) { overflow=d; }
    public Colouring clone() {
        return new StephenOrbitTrap1b_1();
    }
    public String toString() { return "Stephen Orbit Trap1b-R2"; }
}