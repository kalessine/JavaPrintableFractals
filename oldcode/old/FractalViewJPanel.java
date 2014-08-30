/*
 * FractalViewJPanel.java
 *
 * Created on November 7, 2006, 8:51 PM
 */

package fractal.gui.old;

import com.lowagie.text.PageSize;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import fractal.producer.formula.Mandelbrot;
import fractal.producer.formula.Formula;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
/**
 *
 * @author  james
 */
import java.math.BigDecimal;
import java.math.MathContext;
import fractal.producer.calc.*;
import fractal.producer.*;
import fractal.producer.colour.*;
public class FractalViewJPanel extends javax.swing.JPanel implements Runnable {
    
    
    public static final int ASPECT_RATIO_SQUARE = 0;
    public static final int ASPECT_RATIO_A_PORTRAIT = 1;
    public static final int ASPECT_RATIO_A_LANDSCAPE = 2;
    
    FractalImage fractal = new FractalImage();
    BufferedImage image = null
    
    /** Creates new form FractalViewJPanel */
    public FractalViewJPanel() {
        initComponents();
    }
    
    public void paint(Graphics g) {
        if( image != null )
        if( getWidth()!=image.getWidth()||getHeight()!=image.getHeight()){
            regenerate();
        }
        if( image == null )regenerate();
        if( image!=null ) g.drawImage(bi,0,0,null);
    }
    
    public synchronized void regenerate() {
        if( fractal.isRendering())fractal.interruptRender();
        try{
            while(fractal.isRendering() )
               Thread.sleep(10);
        }catch(InterruptedException tie) {}
        fractal.startRender();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ZoomInHandler(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ZoomInHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ZoomInHandler
// TODO add your handling code here:
        if( evt.getButton()==MouseEvent.BUTTON1 ){
           double newWidth = fractWidth.doubleValue()/2d;
           double newHeight = fractHeight.doubleValue()/2d;
           double step = fractWidth.doubleValue()/getWidth();
           double xp= this.fractX.doubleValue()+step*evt.getX();
           step = fractHeight.doubleValue()/getHeight();
           double yp = this.fractY.doubleValue()+step*evt.getY();
           //xp = convertXPoint(evt.getX()).doubleValue();
           //yp = convertYPoint(evt.getY()).doubleValue();
           fractX = new BigDecimal(xp-newWidth/2d);
           fractY = new BigDecimal(yp-newHeight/2d);
           fractWidth = new BigDecimal(newWidth);
           fractHeight=new BigDecimal(newHeight);
           regenerate();
        }else if( evt.getButton()==MouseEvent.BUTTON3){
           double newWidth = fractWidth.doubleValue()*2;
           double newHeight = fractHeight.doubleValue()*2;
           double step = fractWidth.doubleValue()/getWidth();
           double xp= this.fractX.doubleValue()+step*evt.getX();
           step = fractHeight.doubleValue()/getHeight();
           double yp = this.fractY.doubleValue()+step*evt.getY();
           //xp = convertXPoint(evt.getX()).doubleValue();
           //yp = convertYPoint(evt.getY()).doubleValue();
           fractX = new BigDecimal(xp-newWidth/2d);
           fractY = new BigDecimal(yp-newHeight/2d);
           fractWidth = new BigDecimal(newWidth);
           fractHeight=new BigDecimal(newHeight);
           regenerate();
        }else if( evt.getButton()==MouseEvent.BUTTON2){
           double newWidth = fractWidth.doubleValue();
           double newHeight = fractHeight.doubleValue();
           double step = fractWidth.doubleValue()/getWidth();
           double xp= this.fractX.doubleValue()+step*evt.getX();
           step = fractHeight.doubleValue()/getHeight();
           double yp = this.fractY.doubleValue()+step*evt.getY();
           //xp = convertXPoint(evt.getX()).doubleValue();
           //yp = convertYPoint(evt.getY()).doubleValue();
           fractX = new BigDecimal(xp-newWidth/2d);
           fractY = new BigDecimal(yp-newHeight/2d);
           fractWidth = new BigDecimal(newWidth);
           fractHeight=new BigDecimal(newHeight);
           regenerate();
        }
    }//GEN-LAST:event_ZoomInHandler
    
    private BigDecimal convertXPoint(int x){
        BigDecimal step = fractWidth.divide(new BigDecimal(getWidth()),context);
        return this.fractX.add(step.multiply(new BigDecimal(x,context),context));
    }
    private BigDecimal convertYPoint(int y){
        BigDecimal step = fractHeight.divide(new BigDecimal(getHeight()),context);
        return this.fractX.add(step.multiply(new BigDecimal(y,context),context));
        }
    public BigDecimal getCenterX() {
        return fractX.add(fractWidth.divide(new BigDecimal(2),context),context);
    }
    public BigDecimal getCenterY() {
        return fractY.add(fractHeight.divide(new BigDecimal(2),context),context);
    }
    public int getAspectRatio() {
        return aspectRatio;
    }
    public void setAspectRatio(int i) {
        this.aspectRatio=i;
        this.fractHeight=this.fractWidth.divide(new BigDecimal(getRatio()),context);
        updateWidthAndHeight();
    }
    public double getRatio() {
        if( aspectRatio == ASPECT_RATIO_SQUARE ) {
                return 1d;
        }
        else if( aspectRatio == ASPECT_RATIO_A_PORTRAIT ) {
                return PageSize.A0.width()/PageSize.A0.height();
        }
        else if( aspectRatio == ASPECT_RATIO_A_LANDSCAPE ) {
                return PageSize.A0.height()/PageSize.A0.width();
        }
        else return 1d;
    }
    public void updateWidthAndHeight() {
        Rectangle bounds = this.getBounds();
        int newHeight =(int)(bounds.width/getRatio());
        if( bounds.height!=newHeight ){ 
            bounds.height=newHeight;
            setBounds(bounds);
        }
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * Getter for property colorModel.
     * @return Value of property colorModel.
     */
    public ColourMap getColourMap() {
        return this.colorModel;
    }

    /**
     * Setter for property colorModel.
     * @param colorModel New value of property colorModel.
     */
    public void setColourMap(ColourMap colorModel) {
        this.colorModel = colorModel;
    }



    /**
     * Getter for property producer.
     * @return Value of property producer.
     */
    public Formula getProducer() {
        return this.producer;
    }

    /**
     * Setter for property producer.
     * @param producer New value of property producer.
     */
    public void setProducer(Formula producer) {
        this.producer = producer;
        this.colorModel.setMapSize(producer.getMaxIteration());
    }

    /**
     * Getter for property x.
     * @return Value of property x.
     */
    public BigDecimal getFractX() {
        return this.fractX;
    }

    /**
     * Setter for property x.
     * @param x New value of property x.
     */
    public void setFractX(BigDecimal fractX) {
        this.fractX = fractX;
    }

    /**
     * Getter for property y.
     * @return Value of property y.
     */
    public BigDecimal getFractY() {
        return this.fractY;
    }

    /**
     * Setter for property y.
     * @param y New value of property y.
     */
    public void setFractY(BigDecimal fractY) {
        this.fractY = fractY;
    }

    /**
     * Holds value of property fractWidth.
     */
    

    /**
     * Getter for property fractWidth.
     * @return Value of property fractWidth.
     */
    public BigDecimal getFractWidth() {
        return this.fractWidth;
    }

    /**
     * Setter for property fractWidth.
     * @param fractWidth New value of property fractWidth.
     */
    public void setFractWidth(BigDecimal fractWidth) {
        this.fractWidth = fractWidth;
    }

    /**
     * Holds value of property fractHeight.
     */
    

    /**
     * Getter for property fractHeight.
     * @return Value of property fractHeight.
     */
    public BigDecimal getFractHeight() {
        return this.fractHeight;
    }

    /**
     * Setter for property fractHeight.
     * @param fractHeight New value of property fractHeight.
     */
    public void setFractHeight(BigDecimal fractHeight) {
        this.fractHeight = fractHeight;
    }
    public BufferedImage getBufferedImage() { return bi; }
    public void setPrecision(int i) {
        this.precision = i;
        switch(precision) {
            case PRECISION_HARDWARE:context = MathContext.DECIMAL64;break;
            case PRECISION_32_PLACES:context = new MathContext(32);break;
            case PRECISION_64_PLACES:context = new MathContext(64);break;
            case PRECISION_128_PLACES:context = new MathContext(128);break;
        }
    }
    public int getPrecision() { 
        return precision;
    }
    public MathContext getMathContext() { return context; }
}