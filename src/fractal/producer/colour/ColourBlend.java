/*
 * ColourBlend.java
 *
 * Created on 5 March 2007, 12:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.colour;

import fractal.gui.panel.util.editors.EnumPropertyEditorSupport;
import java.awt.Color;
import java.beans.PropertyEditorManager;
import java.io.Serializable;

/**
 *
 * @author Owner
 */
public class ColourBlend implements Serializable {
    public static final int BLEND_SOLID      = 0;
    public static final int BLEND_QUARTER    = 1;
    public static final int BLEND_HALF       = 2;
    public static final int BLEND_THREEQUARTER   = 3;
    public static final int BLEND_FACTOR     = 4;
    public static final int BLEND_XOR1   = 5;
    public static final int BLEND_XOR2   = 6;
    public static final int BLEND_XOR3   = 7;
    public static final int BLEND_AND1   = 8;
    public static final int BLEND_AND2   = 9;
    public static final int BLEND_AND3   = 10;
    public static final int BLEND_OR1   = 11;
    public static final int BLEND_OR2   = 12;
    public static final int BLEND_OR3   = 13;
    
    public static final String SOLID_STR = "Solid";
    public static final String QUARTER_STR = "Quarter";
    public static final String HALF_STR = "Half";
    public static final String THREEQUARTER_STR = "ThreeQuarter";
    public static final String FACTOR_STR = "Factor";
    public static final String XOR1_STR = "Xor-1";
    public static final String XOR2_STR = "Xor-2";
    public static final String XOR3_STR = "Xor-3";
    public static final String AND1_STR = "And-1";
    public static final String AND2_STR = "And-2";
    public static final String AND3_STR = "And-3";
    public static final String OR1_STR = "Or-1";
    public static final String OR2_STR = "Or-2";
    public static final String OR3_STR = "Or-3";
    
    public static final ColourBlend SOLID = new ColourBlend(BLEND_SOLID,SOLID_STR);
    public static final ColourBlend QUARTER = new ColourBlend(BLEND_QUARTER,QUARTER_STR);
    public static final ColourBlend HALF = new ColourBlend(BLEND_HALF,HALF_STR);
    public static final ColourBlend THREEQUARTER = new ColourBlend(BLEND_THREEQUARTER,THREEQUARTER_STR);
    public static final ColourBlend FACTOR = new ColourBlend(BLEND_FACTOR,FACTOR_STR);
    public static final ColourBlend XOR1 = new ColourBlend(BLEND_XOR1,XOR1_STR);
    public static final ColourBlend XOR2 = new ColourBlend(BLEND_XOR2,XOR2_STR);
    public static final ColourBlend XOR3 = new ColourBlend(BLEND_XOR3,XOR3_STR);
    public static final ColourBlend AND1 = new ColourBlend(BLEND_AND1,AND1_STR);
    public static final ColourBlend AND2 = new ColourBlend(BLEND_AND2,AND2_STR);
    public static final ColourBlend AND3 = new ColourBlend(BLEND_AND3,AND3_STR);
    public static final ColourBlend OR1 = new ColourBlend(BLEND_OR1,OR1_STR);
    public static final ColourBlend OR2 = new ColourBlend(BLEND_OR2,OR2_STR);
    public static final ColourBlend OR3 = new ColourBlend(BLEND_OR3,OR3_STR);

    /**
     * All values
     */
    public static final ColourBlend[] enumValues = { SOLID,
    QUARTER,
    HALF,
    THREEQUARTER,
    FACTOR,
    XOR1,
    XOR2,
    XOR3,
    AND1,
    AND2,
    AND3,
    OR1,
    OR2,
    OR3
    };
    
    private String desc;
    
    private int val;
    private ColourBlend(int val, String desc){
        this.desc = desc;
        this.val = val;
    }
    
    /**
     * @return description
     */
    public String toString(){
        return desc;
    }
    
    /**
     * Convenience for enumeration switching
     * @return value.
     */
    public int toInt(){
        return val;
    }
    
    public Object readResolve() {
        switch(val){
            case BLEND_SOLID:
                return SOLID;
            case BLEND_QUARTER:
                return QUARTER;
            case BLEND_HALF:
                return HALF;
            case BLEND_THREEQUARTER:
                return THREEQUARTER;
            case BLEND_FACTOR:
                return FACTOR;
            case BLEND_XOR1:
                return XOR1;
            case BLEND_XOR2:
                return XOR2;
            case BLEND_XOR3:
                return XOR3;
            case BLEND_AND1:
                return AND1;
            case BLEND_AND2:
                return AND2;
            case BLEND_AND3:
                return AND3;
            case BLEND_OR1:
                return OR1;
            case BLEND_OR2:
                return OR2;
            case BLEND_OR3:
                return OR3;
            default:
                throw new Error("Unknown Colour Blend value");
        }
    }
    
    /**
     * Property editor for the <code>Anchor</code> type.
     * @see com.sun.glf.util.EnumPropertyEditorSupport
     */
    static public class ColourBlendPropertyEditor extends EnumPropertyEditorSupport{
        /**
         * This method is intended for use when generating Java code to set
         * the value of the property.  It returns a fragment of Java code
         * that can be used to initialize a variable with the current property
         * value.
         */
        public String getJavaInitializationString() {
            ColourBlend val = (ColourBlend)getValue();
            if(val==null)
                return "null";
            switch(val.toInt()){
                case BLEND_SOLID:
                    return "Solid";
                case BLEND_QUARTER:
                    return "Quarter";
                case BLEND_HALF:
                    return "Half";
                case BLEND_THREEQUARTER:
                    return "ThreeQuarter";
                case BLEND_FACTOR:
                    return "Factor";
                case BLEND_XOR1:
                    return "Xor-1";
                case BLEND_XOR2:
                    return "Xor-2";
                case BLEND_XOR3:
                    return "Xor-3";
                case BLEND_AND1:
                    return "And-1";
                case BLEND_AND2:
                    return "And-2";
                case BLEND_AND3:
                    return "And-3";
                case BLEND_OR1:
                    return "Or-1";
                case BLEND_OR2:
                    return "Or-2";
                case BLEND_OR3:
                    return "Or-3";
                default:
                    throw new Error("Unknown Blend value");
            }
        }
        
        public ColourBlendPropertyEditor(){
            super(enumValues);
        }
    }
    
    /**
     * Static initializer registers the editor with the property editor
     * manager
     */
    static {
        PropertyEditorManager.registerEditor(ColourBlend.class, ColourBlendPropertyEditor.class);
    }
    public Color blend(Color c1,Color c2) {
       return blend(c1,c2,1d);
    }
    public Color blend(Color c1,Color c2,double factor) {
        switch(this.val){
            case ColourBlend.BLEND_SOLID:return c2;
            case ColourBlend.BLEND_QUARTER:
                return ColorUtil.blend(c1,c2,0.75d);
            case ColourBlend.BLEND_HALF:
                return ColorUtil.blend(c1,c2,0.5d);
            case ColourBlend.BLEND_THREEQUARTER:
                return ColorUtil.blend(c1,c2,0.25d);
            case ColourBlend.BLEND_FACTOR:
                return ColorUtil.blend(c1,c2,1d-factor);
            case ColourBlend.BLEND_XOR1:{
                int r1 = c1.getRed();
                int g1 = c1.getGreen();
                int b1 = c1.getBlue();
                int r2 = c2.getRed();
                int g2 = c2.getGreen();
                int b2 = c2.getBlue();
                int r = r1^r2;
                int g = g1^g2;
                int b = b1^b2;
               //System.out.println(""+new Color(r,g,b));
                return new Color(r,g,b);}
            case ColourBlend.BLEND_XOR2:{
                int r1 = c1.getRed();
                int g1 = c1.getGreen();
                int b1 = c1.getBlue();
                int r2 = c2.getRed();
                int g2 = c2.getGreen();
                int b2 = c2.getBlue();
                int r = r1^b2;
                int g = g1^r2;
                int b = b1^g2;
               //System.out.println(""+new Color(r,g,b));
                return new Color(r,g,b);
            }
            case ColourBlend.BLEND_XOR3:{
                int r1 = c1.getRed();
                int g1 = c1.getGreen();
                int b1 = c1.getBlue();
                int r2 = c2.getRed();
                int g2 = c2.getGreen();
                int b2 = c2.getBlue();
                int r = r1^g2;
                int g = g1^b2;
                int b = b1^r2;
               //System.out.println(""+new Color(r,g,b));
                return new Color(r,g,b);
                }
            case ColourBlend.BLEND_AND1:{
                int r1 = c1.getRed();
                int g1 = c1.getGreen();
                int b1 = c1.getBlue();
                int r2 = c2.getRed();
                int g2 = c2.getGreen();
                int b2 = c2.getBlue();
                int r = r1&r2;
                int g = g1&g2;
                int b = b1&b2;
               //System.out.println(""+new Color(r,g,b));
                return new Color(r,g,b);
                }
            case ColourBlend.BLEND_AND2:{
                int r1 = c1.getRed();
                int g1 = c1.getGreen();
                int b1 = c1.getBlue();
                int r2 = c2.getRed();
                int g2 = c2.getGreen();
                int b2 = c2.getBlue();
                int r = r1&b2;
                int g = g1&r2;
                int b = b1&g2;
               //System.out.println(""+new Color(r,g,b));
                return new Color(r,g,b);
                }
            case ColourBlend.BLEND_AND3:{
                int r1 = c1.getRed();
                int g1 = c1.getGreen();
                int b1 = c1.getBlue();
                int r2 = c2.getRed();
                int g2 = c2.getGreen();
                int b2 = c2.getBlue();
                int r = r1&g2;
                int g = g1&r2;
                int b = b1&b2;
               //System.out.println(""+new Color(r,g,b));
                return new Color(r,g,b);
                }
            case ColourBlend.BLEND_OR1:{
                int r1 = c1.getRed();
                int g1 = c1.getGreen();
                int b1 = c1.getBlue();
                int r2 = c2.getRed();
                int g2 = c2.getGreen();
                int b2 = c2.getBlue();
                int r = r1|r2;
                int g = g1|g2;
                int b = b1|b2;
               //System.out.println(""+new Color(r,g,b));
                return new Color(r,g,b);
                }
            case ColourBlend.BLEND_OR2:{
                int r1 = c1.getRed();
                int g1 = c1.getGreen();
                int b1 = c1.getBlue();
                int r2 = c2.getRed();
                int g2 = c2.getGreen();
                int b2 = c2.getBlue();
                int r = r1|b2;
                int g = g1|r2;
                int b = b1|g2;
               //System.out.println(""+new Color(r,g,b));
                return new Color(r,g,b);
                }
            case ColourBlend.BLEND_OR3:{
                int r1 = c1.getRed();
                int g1 = c1.getGreen();
                int b1 = c1.getBlue();
                int r2 = c2.getRed();
                int g2 = c2.getGreen();
                int b2 = c2.getBlue();
                int r = r1|g2;
                int g = g1|r2;
                int b = b1|b2;
               //System.out.println(""+new Color(r,g,b));
                return new Color(r,g,b);
                }

            default:return c1;
        }
        
    }
}
