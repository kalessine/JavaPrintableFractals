package fractal.producer.formula;

import fractal.gui.panel.util.editors.EnumPropertyEditorSupport;
import java.beans.*;
import java.io.*;
public final class MergeZValue implements Serializable{
    
    
    public static final int MERGE_PIXEL_PIXEL           = 0;
    public static final int MERGE_PIXEL_TRANSFORMED     = 1;
    public static final int MERGE_PIXEL_RESULT          = 2;
    public static final int MERGE_PIXEL_RESULT_CONSTANT = 3;
    
    public static final String PIXEL_STR = "Pixel";
    public static final String TRANSFORMED_STR = "Transformed";
    public static final String RESULT_STR = "Result";
    public static final String RESULT_CONSTANT_STR = "Result Constant";
    public static final MergeZValue PIXEL = new MergeZValue(MERGE_PIXEL_PIXEL, PIXEL_STR);
    public static final MergeZValue TRANSFORMED = new MergeZValue(MERGE_PIXEL_TRANSFORMED, TRANSFORMED_STR);
    public static final MergeZValue RESULT  = new MergeZValue(MERGE_PIXEL_RESULT, RESULT_STR);
    public static final MergeZValue RESULT_CONSTANT  = new MergeZValue(MERGE_PIXEL_RESULT_CONSTANT, RESULT_CONSTANT_STR);
    
    
    /**
     * All values
     */
    public static final MergeZValue[] enumValues = { PIXEL,
    TRANSFORMED, RESULT,RESULT_CONSTANT
    };
    
    private String desc;
    private int val;
    
    /**
     * Constructor is private so that no other instances than
     * the one in the enumeration can be created.
     * @see #readResolve
     */
    private MergeZValue(int val, String desc){
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
            case MERGE_PIXEL_PIXEL:
                return PIXEL;
            case MERGE_PIXEL_TRANSFORMED:
                return TRANSFORMED;
            case MERGE_PIXEL_RESULT:
                return RESULT;
            case MERGE_PIXEL_RESULT_CONSTANT:
                return RESULT_CONSTANT;
            default:
                throw new Error("Unknown Merge Pixel value");
        }
    }
    
    /**
     * Property editor for the <code>Anchor</code> type.
     * @see com.sun.glf.util.EnumPropertyEditorSupport
     */
    static public class MergePixelValuePropertyEditor extends EnumPropertyEditorSupport{
        /**
         * This method is intended for use when generating Java code to set
         * the value of the property.  It returns a fragment of Java code
         * that can be used to initialize a variable with the current property
         * value.
         */
        public String getJavaInitializationString() {
            MergeResult val = (MergeResult)getValue();
            if(val==null)
                return "null";
            switch(val.toInt()){
                case MERGE_PIXEL_PIXEL:
                    return PIXEL_STR;
                case MERGE_PIXEL_TRANSFORMED:
                    return TRANSFORMED_STR;
                case MERGE_PIXEL_RESULT:
                    return RESULT_STR;
                case MERGE_PIXEL_RESULT_CONSTANT:
                    return RESULT_CONSTANT_STR;
                default:
                    throw new Error("Unknown Merge Pixel value");
            }
        }
        
        public MergePixelValuePropertyEditor(){
            super(enumValues);
        }
    }
    
    /**
     * Static initializer registers the editor with the property editor
     * manager
     */
    static {
        PropertyEditorManager.registerEditor(MergeZValue.class, MergePixelValuePropertyEditor.class);
    }
}
