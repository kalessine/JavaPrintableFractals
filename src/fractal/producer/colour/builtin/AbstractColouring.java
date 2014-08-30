/*
 * AbstractColouring.java
 *
 * Created on 5 March 2007, 12:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.colour.builtin;

import fractal.producer.calc.M;
import fractal.producer.colour.ColourBlend;
import fractal.producer.colour.ColourMap;
import fractal.producer.colour.Colouring;
import fractal.producer.result.PixelValue;
import fractal.producer.result.Result;
import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author Owner
 */
public class AbstractColouring implements Colouring {
    
    ColourBlend blend = ColourBlend.SOLID;
     double blendFactor = 0.5;
    boolean inColour = false;
    boolean outColour = true;
    boolean trapIterations = false;
    int minIterations=0;
    int maxIterations=255;
    boolean trapReal = false;
    double minReal = 0d;
    double maxReal=500d;
    boolean trapModSquared = false;
    double minModSquared = 0d;
    double maxModSquared=500d;

    
    public void setBlend(ColourBlend blend) {this.blend=blend;}
    public ColourBlend getBlend() { return blend; }
    public void setBlendFactor(double d) { blendFactor=d; }
    public double getBlendFactor() { return blendFactor; }
    public boolean getTrapInColour() { return inColour; }
    public void setTrapInColour(boolean b) { inColour=b; }
    public boolean getTrapOutColour() { return outColour; }
    public void setTrapOutColour(boolean b) { outColour=b; }
    
    public void setTrapIterations(boolean b) { trapIterations=b; }
    public boolean getTrapIterations() { return trapIterations; }
    public void setMinIterations(int i) { minIterations=i; }
    public int getMinIterations() {return minIterations;}
    public void setMaxIterations(int i) { maxIterations=i; }
    public int getMaxIterations() {return maxIterations;}
    
    
    public boolean getTrapReal() { return trapReal; }
    public void setTrapReal(boolean b) { trapReal=b; }
    public void setMinReal(double d) { minReal=d; }
    public double getMinReal() { return minReal; }
    public void setMaxReal(double d) { maxReal=d; }
    public double getMaxReal() { return maxReal; }
    
    public boolean trapImag = false;
    public double minImag = 0d;
    public double maxImag=500d;
    public boolean getTrapImag() { return trapImag; }
    public void setTrapImag(boolean b) { trapImag=b; }
    public void setMinImag(double d) { minImag=d; }
    public double getMinImag() { return minImag; }
    public void setMaxImag(double d) { maxImag=d; }
    public double getMaxImag() { return maxImag; }
    
    public boolean getTrapModSquared() { return trapModSquared; }
    public void setTrapModSquared(boolean b) { trapModSquared=b; }
    public void setMinModSquared(double d) { minModSquared=d; }
    public double getMinModSquared() { return minModSquared; }
    public void setMaxModSquared(double d) { maxModSquared=d; }
    public double getMaxModSquared() { return maxModSquared; }
    
    /** Creates a new instance of AbstractColouring */
    public AbstractColouring() {
    }
    
    public Color getColour(PixelValue pv,ColourMap cmap,Color oldColour,HashMap map){
        Result res = pv.getResult();
        Number n = (Number)map.get("maxIteration");
        boolean in = res.isIn();
        Color c = null;
        if( inColour && in ) c=getColour(pv,cmap);
        if( outColour && !in) c=getColour(pv,cmap);
        if( trapIterations && M.compareTo(res.getIterations(),minIterations)>=0 && M.compareTo(res.getIterations(),maxIterations)<=0) c=getColour(pv,cmap);
        if( trapReal && M.compareTo(res.getZ().getReal(),minReal)>=0 && M.compareTo(res.getZ().getReal(),maxReal)<=0) c=getColour(pv,cmap);
        if( trapImag && M.compareTo(res.getZ().getImag(),minImag)>=0 && M.compareTo(res.getZ().getImag(),maxImag)<=0) c=getColour(pv,cmap);
        if( trapModSquared && M.compareTo(res.getZ().getModSquared(),minModSquared)>=0 && M.compareTo(res.getZ().getModSquared(),maxModSquared)<=0) c=getColour(pv,cmap);
        if( c == null) return oldColour;
        return blend.blend(oldColour,c,blendFactor);
    }
    public Color getColour(PixelValue pv,ColourMap map) {
        return map.getColour(pv.getResult().getIterations());
    }
    public Colouring clone(){
        throw new RuntimeException("Cannot Clone Abstract Class");
    }
}
