package fractal.producer.formula;

import fractal.gui.panel.util.editors.EnumPropertyEditorSupport;
import java.beans.*;
import java.io.*;
public final class MergeIn implements Serializable{
    
    
    public static final int MERGE_IN_FIRST    = 0;
    public static final int MERGE_IN_SECOND   = 1;
    public static final int MERGE_IN_FIRST_OR_SECOND    = 2;
    public static final int MERGE_IN_FIRST_AND_SECOND   = 3;
    public static final int MERGE_IN_FIRST_XOR_SECOND   = 4;
    public static final String FIRST_STR = "First";
    public static final String SECOND_STR = "Second";
    public static final String FIRST_OR_SECOND_STR = "First OR Second";
    public static final String FIRST_AND_SECOND_STR = "First AND Second";
    public static final String FIRST_XOR_SECOND_STR = "First XOR Second";
    
    public static final MergeIn FIRST = new MergeIn(MERGE_IN_FIRST, FIRST_STR);
    public static final MergeIn SECOND = new MergeIn(MERGE_IN_SECOND, SECOND_STR);
    public static final MergeIn FIRST_OR_SECOND = new MergeIn(MERGE_IN_FIRST_OR_SECOND, FIRST_OR_SECOND_STR);
    public static final MergeIn FIRST_AND_SECOND = new MergeIn(MERGE_IN_FIRST_AND_SECOND, FIRST_AND_SECOND_STR);
    public static final MergeIn FIRST_XOR_SECOND = new MergeIn(MERGE_IN_FIRST, FIRST_XOR_SECOND_STR);
    
    /**
     * All values
     */
    public static final MergeIn[] enumValues = { FIRST,
    SECOND,
    FIRST_OR_SECOND,
    FIRST_AND_SECOND,
    FIRST_XOR_SECOND
    };
    
    private String desc;
    private int val;
    
    /**
     * Constructor is private so that no other instances than
     * the one in the enumeration can be created.
     * @see #readResolve
     */
    private MergeIn(int val, String desc){
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
            case MERGE_IN_FIRST:
                return FIRST;
            case MERGE_IN_SECOND:
                return SECOND;
            case MERGE_IN_FIRST_AND_SECOND:
                return FIRST_AND_SECOND;
            case MERGE_IN_FIRST_OR_SECOND:
                return FIRST_OR_SECOND;
            case MERGE_IN_FIRST_XOR_SECOND:
                return FIRST_XOR_SECOND;
            default:
                throw new Error("Unknown Merge In value");
        }
    }
    
    /**
     * Property editor for the <code>Anchor</code> type.
     * @see com.sun.glf.util.EnumPropertyEditorSupport
     */
    static public class MergeInPropertyEditor extends EnumPropertyEditorSupport{
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
                case MERGE_IN_FIRST:
                    return "First";
                case MERGE_IN_SECOND:
                    return "Second";
                case MERGE_IN_FIRST_AND_SECOND:
                    return "First AND Second";
                case MERGE_IN_FIRST_OR_SECOND:
                    return "First OR Second";
                case MERGE_IN_FIRST_XOR_SECOND:
                    return "First XOR Second";
                default:
                    throw new Error("Unknown Merge In value");
            }
        }
        
        public MergeInPropertyEditor(){
            super(enumValues);
        }
    }
    
    /**
     * Static initializer registers the editor with the property editor
     * manager
     */
    static {
        PropertyEditorManager.registerEditor(MergeIn.class, MergeInPropertyEditor.class);
    }
}
