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
public class AngleToPointOrbitTrap implements OrbitTrap {
    
    /** Creates a new instance of SimpleOrbitTrap */
    public AngleToPointOrbitTrap() {
    }
    
    ComplexNumber p = new ComplexDouble(0d,0d);
    
    private double lowest = 2*Math.PI;
    private double highest = 0d;
    private double total = 0d;
    private int count = 0;
    public void init(HashMap map){
        lowest = 2*Math.PI;
        highest = 0d;
        total = 0d;
        count=0;
        
    }
    ColourBlend blend = ColourBlend.SOLID;
    
    public void hit(HashMap map,Number iterations){
        if( M.compareTo(iterations,0)==0)return;
        ComplexNumber z = (ComplexNumber)map.get(Formula.Z);
        z = z.subtr(p);
        double a = Math.atan2(z.getImag().doubleValue(),z.getReal().doubleValue());
        if( a < lowest ) lowest=a;
        if( a > highest ) highest=a;
        total+=a;
        count+=1;
    }
    
    public Object getTrapValue(){
        if( count==0) return 0d;
        else
           return total/count;
    }
    public ColourBlend getBlend() { return blend; }
    public void setBlend(ColourBlend c) { this.blend=blend; }
    double blendFactor = 0.5d;
    public double getBlendFactor() { return blendFactor; }
    public void setBlendFactor(double d) { blendFactor=d; }
    public Color getColour(PixelValue pv,ColourMap cmap,Color oldColour,HashMap map) {
        Result res = pv.getResult();
        Integer orbitindex = (Integer)map.get(OrbitTrap.ORBIT_INDEX);
        Double r = (Double)res.getTrapValue(orbitindex.intValue());
        //System.out.println("av="+r);
        if( r==null)return oldColour;
        if( r > lowAngle && r < highAngle) {
            Color c = cmap.getColour( r*100);
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
    double lowAngle = 0d;
    double highAngle = 30d;
    public double getLowAngle() { return lowAngle; }
    public double getHighAngle() { return highAngle; }
    public void setLowAngle(double d)  { lowAngle=d;}
    public void setHighAngle(double d) { highAngle=d; }
    

    public Colouring clone() {
        return new AngleToPointOrbitTrap();
    }
}