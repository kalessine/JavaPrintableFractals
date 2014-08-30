package fractal.producer.formula;

import fractal.gui.panel.util.editors.EnumPropertyEditorSupport;
import fractal.producer.calc.ComplexNumber;
import java.beans.*;
import java.io.*;
public final class MergeConstants implements Serializable{
    
    
    public static final int MERGE_CONSTANTS_NEW_CONSTANTS      = 0;
    public static final int MERGE_CONSTANTS_KEEP_CONSTANTS     = 1;
    public static final int MERGE_CONSTANTS_THREEQUARTER     = 2;
    public static final int MERGE_CONSTANTS_HALF     = 3;
    public static final int MERGE_CONSTANTS_QUARTER     = 4;
    public static final int MERGE_CONSTANTS_TWOTHIRDS     = 5;
    public static final int MERGE_CONSTANTS_THIRD     = 6;
    public static final int MERGE_CONSTANTS_TWOTENTHS     = 7;
    public static final int MERGE_CONSTANTS_FOURTENTHS     = 8;
    public static final int MERGE_CONSTANTS_SIXTENTHS     = 9;
    public static final int MERGE_CONSTANTS_EIGHTTENTHS     = 10;
    public static final int MERGE_CONSTANTS_DOUBLE     = 11;
    public static final int MERGE_CONSTANTS_TRIPLE     = 12;
    public static final int MERGE_CONSTANTS_QUADRUPLE     = 13;
    public static final int MERGE_CONSTANTS_SQUARE     = 14;
    public static final int MERGE_CONSTANTS_CUBE     = 15;
    public static final int MERGE_CONSTANTS_POWER4     = 16;
    public static final int MERGE_CONSTANTS_POWER5    = 17;
    public static final String KEEP_STR = "Keep Constants";
    public static final String NEW_STR = "New Constants";
    public static final String THREEQUARTER_STR = "ThreeQuarter Constants";
    public static final String HALF_STR = "Half Constants";
    public static final String QUARTER_STR = "Quarter Constants";
    public static final String TWOTHIRDS_STR = "Two Third Constants";
    public static final String THIRD_STR = "Third Constants";
    public static final String TWOTENTHS_STR = "Two Tenth Constants";
    public static final String FOURTENTHS_STR = "Four Tenth Constants";
    public static final String SIXTENTHS_STR = "Six Tenth Constants";
    public static final String EIGHTTENTHS_STR = "Eight Tenth Constants";
    public static final String DOUBLE_STR = "Double Constants";
    
    public static final String TRIPLE_STR = "Triple Constants";
    public static final String QUADRUPLE_STR = "Quadruple Constants";
    public static final String SQUARE_STR = "Square Constants";
    public static final String CUBE_STR = "Cube Constants";
    public static final String POWER4_STR = "Power Of 4 Constants";
    public static final String POWER5_STR = "Power Of 5 Constants";
    
    public static final MergeConstants NEW_CONSTANTS = new MergeConstants(MERGE_CONSTANTS_NEW_CONSTANTS,NEW_STR);
    public static final MergeConstants KEEP_CONSTANTS = new MergeConstants(MERGE_CONSTANTS_KEEP_CONSTANTS, KEEP_STR);
    public static final MergeConstants THREEQUARTER = new MergeConstants(MERGE_CONSTANTS_THREEQUARTER,THREEQUARTER_STR);
    public static final MergeConstants HALF = new MergeConstants(MERGE_CONSTANTS_HALF,HALF_STR);
    public static final MergeConstants QUARTER = new MergeConstants(MERGE_CONSTANTS_QUARTER,QUARTER_STR);
    public static final MergeConstants TWOTHIRDS = new MergeConstants(MERGE_CONSTANTS_TWOTHIRDS,TWOTHIRDS_STR);
    public static final MergeConstants THIRD = new MergeConstants(MERGE_CONSTANTS_THIRD,THIRD_STR);
    public static final MergeConstants TWOTENTHS = new MergeConstants(MERGE_CONSTANTS_TWOTENTHS,TWOTENTHS_STR);
    public static final MergeConstants FOURTENTHS = new MergeConstants(MERGE_CONSTANTS_FOURTENTHS,FOURTENTHS_STR);
    public static final MergeConstants SIXTENTHS = new MergeConstants(MERGE_CONSTANTS_SIXTENTHS,SIXTENTHS_STR);
    public static final MergeConstants EIGHTTENTHS = new MergeConstants(MERGE_CONSTANTS_EIGHTTENTHS,EIGHTTENTHS_STR);
    
    
    
    public static final MergeConstants DOUBLE = new MergeConstants(MERGE_CONSTANTS_DOUBLE,DOUBLE_STR);
    public static final MergeConstants TRIPLE = new MergeConstants(MERGE_CONSTANTS_TRIPLE,TRIPLE_STR);
    public static final MergeConstants QUADRUPLE = new MergeConstants(MERGE_CONSTANTS_QUADRUPLE,QUADRUPLE_STR);
    public static final MergeConstants SQUARE = new MergeConstants(MERGE_CONSTANTS_SQUARE,SQUARE_STR);
    public static final MergeConstants CUBE = new MergeConstants(MERGE_CONSTANTS_CUBE,CUBE_STR);
    public static final MergeConstants POWER4 = new MergeConstants(MERGE_CONSTANTS_POWER4,POWER4_STR);
    public static final MergeConstants POWER5 = new MergeConstants(MERGE_CONSTANTS_POWER5,POWER5_STR);
    
    /**
     * All values
     */
    public static final MergeConstants[] enumValues = { KEEP_CONSTANTS,
    NEW_CONSTANTS,
    THREEQUARTER,HALF,QUARTER,TWOTHIRDS,THIRD,TWOTENTHS,FOURTENTHS,SIXTENTHS,EIGHTTENTHS,
    DOUBLE,TRIPLE,QUADRUPLE,SQUARE,
    CUBE,POWER4,POWER5
    };
    
    private String desc;
    private int val;
    
    /**
     * Constructor is private so that no other instances than
     * the one in the enumeration can be created.
     * @see #readResolve
     */
    private MergeConstants(int val, String desc){
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
            case MERGE_CONSTANTS_KEEP_CONSTANTS:
                return KEEP_CONSTANTS;
            case MERGE_CONSTANTS_NEW_CONSTANTS:
                return NEW_CONSTANTS;
            case MERGE_CONSTANTS_DOUBLE:
                return DOUBLE;
            case MERGE_CONSTANTS_TRIPLE:
                return TRIPLE;
            case MERGE_CONSTANTS_QUADRUPLE:
                return QUADRUPLE;
            case MERGE_CONSTANTS_SQUARE:
                return SQUARE;
            case MERGE_CONSTANTS_CUBE:
                return CUBE;
            case MERGE_CONSTANTS_POWER4:
                return POWER4;
            case MERGE_CONSTANTS_POWER5:
                return POWER5;
            default:
                throw new Error("Unknown Merge In value");
        }
    }
    public Object processConstant(Object o) {
        switch(this.val) {
            case MERGE_CONSTANTS_NEW_CONSTANTS:return o;
            case MERGE_CONSTANTS_KEEP_CONSTANTS:return o;
            case MERGE_CONSTANTS_THREEQUARTER:return threequarter(o);
            case MERGE_CONSTANTS_HALF:return half(o);
            case MERGE_CONSTANTS_QUARTER:return quarter(o);
            case MERGE_CONSTANTS_TWOTHIRDS:return twothirds(o);
            case MERGE_CONSTANTS_THIRD:return third(o);
            case MERGE_CONSTANTS_TWOTENTHS:return twotenths(o);
            case MERGE_CONSTANTS_FOURTENTHS:return fourtenths(o);
            case MERGE_CONSTANTS_SIXTENTHS:return sixtenths(o);
            case MERGE_CONSTANTS_EIGHTTENTHS:return eighttenths(o);
            case MERGE_CONSTANTS_DOUBLE:return times2(o);
            case MERGE_CONSTANTS_TRIPLE:return times3(o);
            case MERGE_CONSTANTS_QUADRUPLE:return times4(o);
            case MERGE_CONSTANTS_SQUARE:return square(o);
            case MERGE_CONSTANTS_CUBE:return cube(o);
            case MERGE_CONSTANTS_POWER4:return power4(o);
            case MERGE_CONSTANTS_POWER5:return power5(o);
            default:return o;
        }
    }
    public Object threequarter(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*0.75;
        if( o instanceof Integer ) return (int)(((Integer)o).intValue()*0.75);
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(0.75);
        return o;
    }
    public Object half(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*0.5;
        if( o instanceof Integer ) return (int)(((Integer)o).intValue()*0.5);
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(0.5d);
        return o;
    }
    public Object quarter(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*0.25d;
        if( o instanceof Integer ) return (int)(((Integer)o).intValue()*0.25d);
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(0.25d);
        return o;
    }

    private static double twoonthree = 2d/3d;
    public Object twothirds(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*twoonthree;
        if( o instanceof Integer ) return (int)(((Integer)o).intValue()*twoonthree);
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(twoonthree);
        return o;
    }
    private static double oneonthree = 1d/3d;
    public Object third(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*oneonthree;
        if( o instanceof Integer ) return (int)(((Integer)o).intValue()*oneonthree);
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(oneonthree);
        return o;
    }
    public Object twotenths(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*0.2d;
        if( o instanceof Integer ) return (int)(((Integer)o).intValue()*0.2d);
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(0.2d);
        return o;
    }
    public Object fourtenths(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*0.4;
        if( o instanceof Integer ) return (int)(((Integer)o).intValue()*0.4);
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(0.4);
        return o;
    }
    public Object sixtenths(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*0.6;
        if( o instanceof Integer ) return (int)(((Integer)o).intValue()*0.6);
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(0.6);
        return o;
    }
    public Object eighttenths(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*0.8;
        if( o instanceof Integer ) return (int)(((Integer)o).intValue()*0.8);
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(0.8);
        return o;
    }
    
    public Object times2(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*2;
        if( o instanceof Integer ) return ((Integer)o).intValue()*2;
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(2d);
        return o;
    }
    public Object times3(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*3;
        if( o instanceof Integer ) return ((Integer)o).intValue()*3;
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(3d);
        return o;
    }
    public Object times4(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*4;
        if( o instanceof Integer ) return ((Integer)o).intValue()*4;
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).mult(4d);
        return o;
    }
    public Object square(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*((Double)o).doubleValue();
        if( o instanceof Integer ) return ((Integer)o).intValue()*((Integer)o).intValue();
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).sqr();
        return o;
    }
    public Object cube(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*((Double)o).doubleValue()*((Double)o).doubleValue();
        if( o instanceof Integer ) return ((Integer)o).intValue()*((Integer)o).intValue()*((Integer)o).intValue();
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).cube();
        return o;
    }
    public Object power4(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*((Double)o).doubleValue()*((Double)o).doubleValue()*((Double)o).doubleValue();
        if( o instanceof Integer ) return ((Integer)o).intValue()*((Integer)o).intValue()*((Integer)o).intValue()*((Integer)o).intValue();
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).sqr().sqr();
        return o;
    }
    public Object power5(Object o) {
        if( o instanceof Double ) return ((Double)o).doubleValue()*((Double)o).doubleValue()*((Double)o).doubleValue()*((Double)o).doubleValue()*((Double)o).doubleValue();
        if( o instanceof Integer ) return ((Integer)o).intValue()*((Integer)o).intValue()*((Integer)o).intValue()*((Integer)o).intValue()*((Integer)o).intValue();
        if( o instanceof ComplexNumber ) return ((ComplexNumber)o).sqr().cube();
        return o;
    }
    
    /**
     *
     *
     * Property editor for the <code>Anchor</code> type.
     * @see com.sun.glf.util.EnumPropertyEditorSupport
     */
    static public class MergeConstantsPropertyEditor extends EnumPropertyEditorSupport{
        /**
         * This method is intended for use when generating Java code to set
         * the value of the property.  It returns a fragment of Java code
         * that can be used to initialize a variable with the current property
         * value.
         */
        public String getJavaInitializationString() {
            MergeConstants val = (MergeConstants)getValue();
            if(val==null)
                return "null";
            switch(val.toInt()){
                case MERGE_CONSTANTS_KEEP_CONSTANTS:
                    return KEEP_STR;
                case MERGE_CONSTANTS_NEW_CONSTANTS:
                    return NEW_STR;
                case MERGE_CONSTANTS_THREEQUARTER:
                    return THREEQUARTER_STR;
                case MERGE_CONSTANTS_HALF:
                    return HALF_STR;
                case MERGE_CONSTANTS_QUARTER:
                    return QUARTER_STR;
                case MERGE_CONSTANTS_TWOTHIRDS:
                    return TWOTHIRDS_STR;
                case MERGE_CONSTANTS_THIRD:
                    return THIRD_STR;
                case MERGE_CONSTANTS_TWOTENTHS:
                    return TWOTENTHS_STR;
                case MERGE_CONSTANTS_FOURTENTHS:
                    return FOURTENTHS_STR;
                case MERGE_CONSTANTS_SIXTENTHS:
                    return SIXTENTHS_STR;
                case MERGE_CONSTANTS_EIGHTTENTHS:
                    return EIGHTTENTHS_STR;
                case MERGE_CONSTANTS_DOUBLE:
                    return DOUBLE_STR;
                case MERGE_CONSTANTS_TRIPLE:
                    return TRIPLE_STR;
                case MERGE_CONSTANTS_QUADRUPLE:
                    return QUADRUPLE_STR;
                case MERGE_CONSTANTS_SQUARE:
                    return SQUARE_STR;
                case MERGE_CONSTANTS_CUBE:
                    return CUBE_STR;
                case MERGE_CONSTANTS_POWER4:
                    return POWER4_STR;
                case MERGE_CONSTANTS_POWER5:
                    return POWER5_STR;
                    
                default:
                    throw new Error("Unknown Merge In value");
            }
        }
        
        public MergeConstantsPropertyEditor(){
            super(enumValues);
        }
    }
    
    /**
     * Static initializer registers the editor with the property editor
     * manager
     */
    static {
        PropertyEditorManager.registerEditor(MergeConstants.class, MergeConstantsPropertyEditor.class);
    }
}
