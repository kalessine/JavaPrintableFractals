/*
 * ColourItem.java
 *
 * Created on 3 February 2007, 18:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.colour;

/**
 *
 * @author Owner
 */
import java.awt.Color;
import java.io.Serializable;
public class ColourItem implements Serializable {
    public static final int NO_BLEND = 0;
    public static final int LEFT_BLEND = 1;
    public static final int RIGHT_BLEND = 2;
    public static final int DOUBLE_BLEND = 3;

    public static final int ONE_QUARTER_BLEND = 4;
    public static final int HALF_BLEND = 5;
    public static final int THREE_QUARTER_BLEND = 6;

    public static final int CRISS_CROSS_BLEND = 7;
    
    private Color color = Color.WHITE;
    private Color stripeColor = null;
    private int blend = NO_BLEND;
    private double length = 1.0d;
    private double leftValue = 0d;
    private double colourCenter = 0d;
    /** Creates a new instance of ColourItem */
    public ColourItem() {
    }
    public ColourItem(Color c) {
        this.color=c;
        this.length=1d;
    }
    public ColourItem(Color c, double length) {
        this.color=c;
        this.length=length;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(double leftValue) {
        this.leftValue = leftValue;
    }
   public double getRightValue() {
       return leftValue+length;
   }
   public String toString() {
       return color+" left="+getLeftValue()+" center="+colourCenter+" length="+length+" stripe="+stripeColor+" blend="+blend;
   }

    public double getColourCenter() {
        return colourCenter;
    }

    public void setColourCenter(double colourCenter) {
        this.colourCenter = colourCenter;
    }

    public Color getStripeColor() {
        return stripeColor;
    }

    public void setStripeColor(Color stripeColor) {
        this.stripeColor = stripeColor;
    }

    public boolean isStriped() {
        return stripeColor!=null;
    }

    public int getBlend() {
        return blend;
    }

    public void setBlend(int blendStripe) {
        this.blend = blendStripe;
    }
}
