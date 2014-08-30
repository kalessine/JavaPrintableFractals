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
import fractal.producer.colour.ColourBlend;
import fractal.producer.colour.ColourMap;
import fractal.producer.colour.Colouring;
import fractal.producer.formula.Formula;
import fractal.producer.orbittrap.OrbitTrap;
import fractal.producer.result.PixelValue;
import fractal.producer.result.Result;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * adapted from Stephen Ferguson's render03.java from EmberJ
 * @author Owner
 */
public class BitmapOrbitTrap implements OrbitTrap {
    
    /** Creates a new instance of SimpleOrbitTrap */
    public BitmapOrbitTrap() {
    }
    double realCenter_ = 0d;
    double imagCenter_=0d;
    double range = 0.5d;
    double rangePerPixel_ = 1d;
    int realCenterPix_ = 0;
    int imagCenterPix_ = 0;
    int width_ = 1;
    int height_ = 1;
    
    boolean trapped = false;
    File file = new File("Select A File");
    BufferedImage image = null;
    public void init(HashMap map){
        trapped=false;
        traps=new ArrayList(0);
        realHitPix=-1;
        imagHitPix=-1;
    }
    ColourBlend blend = ColourBlend.SOLID;
    int imagHitPix;
    int realHitPix;
    List traps = new ArrayList(0);
    public void hit(HashMap map,Number iterations){
        if(trapped&&!multiTrap||(multiTrap&&traps.size()>maxTrap))return;
        //if( iterations.intValue()==0) return;
        double real = ((ComplexNumber)map.get(Formula.Z)).getReal().doubleValue();
        double imaginary = ((ComplexNumber)map.get(Formula.Z)).getImag().doubleValue();
        //if (rotation_ == 0) {
        int deltaRealPix = (int)((real - realCenter_) / rangePerPixel_);
        int deltaImagPix = (int)((imagCenter_ - imaginary) / rangePerPixel_);  // Bigger Y is up.
        //} else {
        //  double deltaReal = real - realCenter_;
        //  double deltaImag = imagCenter_ - imaginary; // Y is upside down on the scene, bigger is up.
        //deltaRealPix = (int)((deltaReal * real2real_ + deltaImag * imag2real_) / rangePerPixel_);
        //deltaImagPix = (int)((deltaReal * real2imag_ + deltaImag * imag2imag_) / rangePerPixel_);
        //}
        int realHitPix = realCenterPix_ + deltaRealPix;
        if (realHitPix < 0 || realHitPix >= width_) {
            return;
        }
        int imagHitPix = imagCenterPix_ + deltaImagPix;
        if (imagHitPix < 0 || imagHitPix >= height_) {
            return;
        }
        //if (transparentPixelP_ && colorMapHit_ == transparentPixel_) {
        // return false;
        //}
//    if (debugCount_++ < 6) {
//      System.out.println("TrapFrame.hitP returning T");
//    }
        this.imagHitPix=imagHitPix;
        this.realHitPix=realHitPix;
        traps.add(new int[]{realHitPix,imagHitPix});
        trapped=true;
    }
    
    public Object getTrapValue(){
        return traps;
    }
    public ColourBlend getBlend() { return blend; }
    public void setBlend(ColourBlend c) { this.blend=c; }
    double blendFactor = 0.5d;
    public double getBlendFactor() { return blendFactor; }
    public void setBlendFactor(double d) { blendFactor=d; }
    public Color getColour(PixelValue pv,ColourMap cmap,Color oldColour,HashMap map) {
        Result res = pv.getResult();
        Integer orbitindex = (Integer)map.get(OrbitTrap.ORBIT_INDEX);
        if( res.getTrapValue(orbitindex.intValue())instanceof ComplexDouble )
            System.out.println("OrbitIndex="+orbitindex);
        List traps = (List)res.getTrapValue(orbitindex.intValue());
        Color c = getColor(traps,oldColour);
        //System.out.println(a.toString());
        //Color c2 = cmap.getColour( a.getMagnitude().doubleValue());
        //Color c = c1;
        return c;
    }
    public Color getColor(List traps,Color back) {
        Color c = back;
        if(multiTrap)
            for(int i=minTrap; i<maxTrap&&i<traps.size();i++){
            int[] tr = (int[])traps.get(i);
            Color p = getPixel(tr[0],tr[1]);
            if( p.getAlpha()!=0){
                c=blend.blend(c,p);
            }
            } else if( traps.size()==1) {
            int[] tr = (int[])traps.get(0);
            Color p = getPixel(tr[0],tr[1]);
            if( p.getAlpha()!=0){
                c=blend.blend(c,p);
            }
            }
        return c;
    }
    public Color getPixel(int x, int y) {
        java.awt.image.Raster raster = image.getRaster();
        java.awt.image.ColorModel colorModel = image.getColorModel();
        Object pixel = raster.getDataElements(x, y, null);
        byte red = (byte)colorModel.getRed(pixel);
        byte green = (byte)colorModel.getGreen(pixel);
        byte blue = (byte)colorModel.getBlue(pixel);
        byte alpha = (byte)colorModel.getAlpha(pixel);
        if( red > 255 ) System.out.println("Red="+red);
        if( green > 255 ) System.out.println("Green="+green);
        if( blue > 255 ) System.out.println("Blue="+blue);
        if( alpha > 255 ) System.out.println("Alpha="+alpha);
        Color c =  new Color((int)(red&0xff),(int)(green&0xff),(int)(blue&0xff),(int)(alpha&0xff));
        return c;
    }
    public double getRealCenter() { return realCenter_; }
    public double getImagCenter() { return imagCenter_; }
    public void setRealCenter(double d)  { realCenter_=d;}
    public void setImagCenter(double d) { imagCenter_=d; }
    
    public double getRange() { return range; }
    public void setRange(double d) { range=d;
    rangePerPixel_ = range/width_;
    }
    boolean multiTrap;
    public boolean getMultiTrap() {
        return multiTrap;
    }
    public void setMultiTrap(boolean b) {
        multiTrap=b;
    }
    int minTrap=1;
    public int getMinTrapValue() { return minTrap; }
    public void setMinTrapValue(int i) { minTrap=i; }
    int maxTrap=30;
    public int getMaxTrapValue() { return maxTrap; }
    public void setMaxTrapValue(int i) { maxTrap=i; }
    
    public Colouring clone() {
        return new BitmapOrbitTrap();
    }
    public String toString() { return "Bitmap Orbit Trap"; }
    
    public File getBitmap() { return file; }
    public void setBitmap(File f) { 
        file=f; 
        try{
        image = ImageIO.read(new FileInputStream(file));
        width_ = image.getWidth();
        height_=image.getHeight();
        }catch(Exception ex){
            System.out.println("damn!="+f);
        file = new File("Select A file");
        width_=0;
        height_=0;
        }
    }
}