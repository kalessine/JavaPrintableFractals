/*
 * Coordinates.java
 *
 * Created on 8 February 2007, 20:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer;

import fractal.producer.calc.BigMath;
import fractal.producer.calc.ComplexDouble;
import fractal.producer.calc.ComplexNumber;
import fractal.producer.calc.ComplexNumberFactory;
import fractal.producer.calc.M;
import fractal.producer.result.PixelValue;
import java.io.Serializable;
import java.math.MathContext;

/**
 *
 * @author Owner
 */
public class Coordinates implements Serializable {
    
    private Number left = null;
    private Number top = null;
    private Number right = null;
    private Number bottom = null;
    private MathContext context = null;
    /** Creates a new instance of Coordinates */
    public Coordinates(MathContext context) {
        this.context=context;
        if( context.getPrecision() < BigMath.DOUBLE_DECIMAL_PLACES ){
            left = 0d;
            top = 0d;
            right = 0d;
            bottom = 0d;
        } else {
            left = BigMath.ZERO;
            top = BigMath.ZERO;
            right = BigMath.ZERO;
            bottom = BigMath.ZERO;
        }
    }
    
    public Number getLeft() {
        return left;
    }
    
    public void setLeft(Number leftX) {
        this.left = leftX;
    }
    
    public Number getTop() {
        return top;
    }
    
    public void setTop(Number topY) {
        this.top = topY;
    }
    
    public Number getWidth() {
        return M.subtract(right,left, getContext());
    }
    
    public Number getHeight() {
        return M.subtract(top,bottom, getContext());
    }
    
    public Number getRight() {
        return right;
    }
    
    public void setRight(Number rightX) {
        this.right = rightX;
    }
    
    public Number getBottom() {
        return bottom;
    }
    
    public void setBottom(Number bottomY) {
        this.bottom = bottomY;
    }
    
    public PixelValue[][] createPixelArray(int width,int height,ComplexNumberFactory factory,PixelValue[][] oldPixels) {
        PixelValue[][] pixels = new PixelValue[height][width];
        Number xstart = this.left;
        Number ystart = this.top;
        System.out.println("Context="+getContext());
        Number xstep = M.div(getWidth(),width, getContext());
        Number ystep = M.div(getHeight(),height, getContext());
        System.out.println("Calculated X+YStep");
        Number y = ystart;
        boolean usedAllOldPixels = false;
        if( oldPixels == null ) usedAllOldPixels = true;
        int used = 0;
        for(int i=0; i<height;i++) {
            Number x = xstart;
            for(int j=0;j<width;j++){
                ComplexNumber pixel = factory.createComplexNumber(x,y);
                if( !usedAllOldPixels )
                    pixels[i][j]=popPixelValue(oldPixels,used);
                if( pixels[i][j]==null) {
                    usedAllOldPixels = true;
                    pixels[i][j]=new PixelValue(pixel);
                }else {
                    used++;
                    pixels[i][j].setValue(pixel);
                }
                x = M.add(x,xstep, getContext());
                //System.out.println("I="+i+" X="+x+" j="+j+" y="+y);
            }
            y = M.sub(y,ystep, getContext());
        }
        return pixels;
    }
    public PixelValue popPixelValue(PixelValue[][] oldPixels,int used) {
        for(int i=(used/oldPixels[0].length);i<oldPixels.length;i++)
            for(int j=0;j<oldPixels[i].length;j++) {
            if( oldPixels[i][j]!=null ){
                PixelValue pv = oldPixels[i][j];
                oldPixels[i][j]=null;
                //System.out.println("Found Old Pixel");
                return pv;
            }
            }
        return null;
    }
    
    public PixelValue[][] createPixelArray(int px,int py,int w,int h,int twidth,int theight,ComplexNumberFactory factory) {
        PixelValue[][] pixels = new PixelValue[h][w];
        Number xstart = this.left;
        Number ystart = this.top;
        Number xstep = M.div(getWidth(),twidth, getContext());
        Number ystep = M.div(getHeight(),theight, getContext());
        xstart = M.add(xstart,M.multiply(px,xstep,context),context);
        ystart = M.sub(ystart,M.multiply(py,ystep,context),context);
        Number y = ystart;
        for(int i=0; i<h;i++) {
            Number x = xstart;
            for(int j=0;j<w;j++){
                ComplexNumber pixel = factory.createComplexNumber(x,y);
                pixels[i][j]=new PixelValue(pixel);
                x = M.add(x,xstep, getContext());
            }
            y = M.sub(y,ystep, getContext());
        }
        return pixels;
    }

    
    public static Coordinates createCoordinates(Number x, Number y, Number w,Number h,MathContext cx) {
        Coordinates c = new Coordinates(cx);
        c.setLeft(x);
        c.setBottom(y);
        c.setRight(M.add(x,w,cx));
        c.setTop(M.add(y,h,cx));
        System.out.println("Left="+c.getLeft());
        System.out.println("Bottom="+c.getBottom());
        System.out.println("right="+c.getRight());
        System.out.println("top="+c.getTop());
        System.out.println("w="+c.getWidth());
        System.out.println("h="+c.getHeight());
        return c;
    }
    
    public MathContext getContext() {
        return context;
    }
    
    public void setContext(MathContext context) {
        this.context = context;
        if( context.getPrecision() > 16 ) {
            left = M.toBigDecimal(left,context);
            bottom = M.toBigDecimal(bottom,context);
            right = M.toBigDecimal(right,context);
            top = M.toBigDecimal(top,context);
        }else {
            left = M.toDouble(left);
            bottom = M.toDouble(bottom);
            right = M.toDouble(right);
            top = M.toDouble(top);
        }
    }
    public String toString() {
        return "Coordinates ["+left+","+top+","+right+","+bottom+"]";
    }
    
    public void zoom(Number x, Number y, double factor){
        Number nwidth = M.div(getWidth(),factor,context);
        Number nheight = M.div(getHeight(),factor,context);
        bottom = M.sub(y,M.div(nheight,2,context),context);
        top = M.add(bottom,nheight,context);
        left = M.sub(x,M.div(nwidth,2,context),context);
        right = M.add(left,nwidth,context);
    }
    public Number getXValue(int x, int w) {
        Number xstart = this.left;
        Number xstep = M.div(getWidth(),w, getContext());
        return M.add(xstart,M.mult(xstep,x,context),context);
    }
    public Number getYValue(int y, int h) {
        Number ystart = this.bottom;
        Number ystep = M.div(getHeight(),h, getContext());
        return M.add(ystart,M.mult(ystep,(h-y),context),context);
    }
    public Number getCenterX() {
        return M.add(getLeft(),M.div(M.sub(getRight(),getLeft(),context),2,context),context);
    }
    public Number getCenterY() {
        return M.add(getBottom(),M.div(M.sub(getTop(),getBottom(),context),2,context),context);
    }
    public ComplexNumber getCenter() {
        if( this.context.getPrecision() <= BigMath.DOUBLE_DECIMAL_PLACES ) {
            return new ComplexDouble(getCenterX().doubleValue(),getCenterY().doubleValue());
        } else {
            System.out.println("Coords REturning Null");
            return null;
        }
    }
}
