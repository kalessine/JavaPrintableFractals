package fractal.producer.formula;

import fractal.gui.panel.util.editors.EnumPropertyEditorSupport;
import java.beans.*;
import java.io.*;
public final class MergeIterations implements Serializable{
    
// Add The Two Iterations Together
    public static final int MERGE_ITERATIONS_ADDITIVE    = 0;
// Subtract Second Formula From One
    public static final int MERGE_ITERATIONS_SUBTRACTIVE = 1;
// Use First Formulas Iterations
    public static final int MERGE_ITERATIONS_FIRST       = 2;
// init iterations to zero and use second formulas iterations
    public static final int MERGE_ITERATIONS_SECOND      = 3;
// init iterations for second formula to first formulas iterations
    public static final int MERGE_ITERATIONS_CHAIN = 4;
    
    public static final String ADDITIVE_STR = "Additive";
    public static final String SUBTRACTIVE_STR = "Subtractive";
    public static final String FIRST_STR = "First";
    public static final String SECOND_STR = "Second";
    public static final String CHAIN_STR = "Chain";
    
    public static final MergeIterations ADDITIVE = new MergeIterations(MERGE_ITERATIONS_ADDITIVE, ADDITIVE_STR);
    public static final MergeIterations SUBTRACTIVE = new MergeIterations(MERGE_ITERATIONS_SUBTRACTIVE, SUBTRACTIVE_STR);
    public static final MergeIterations FIRST = new MergeIterations(MERGE_ITERATIONS_FIRST, FIRST_STR);
    public static final MergeIterations SECOND = new MergeIterations(MERGE_ITERATIONS_SECOND, SECOND_STR);
    public static final MergeIterations CHAIN = new MergeIterations(MERGE_ITERATIONS_CHAIN, CHAIN_STR);
    
    
    /**
     * All values
     */
    public static final MergeIterations[] enumValues = { ADDITIVE,
    SUBTRACTIVE,
    FIRST,
    SECOND,
    CHAIN
    };
    
    private String desc;
    private int val;
    
    /**
     * Constructor is private so that no other instances than
     * the one in the enumeration can be created.
     * @see #readResolve
     */
    private MergeIterations(int val, String desc){
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
            case MERGE_ITERATIONS_ADDITIVE:
                return ADDITIVE;
            case MERGE_ITERATIONS_SUBTRACTIVE:
                return SUBTRACTIVE;
            case MERGE_ITERATIONS_FIRST:
                return FIRST;
            case MERGE_ITERATIONS_SECOND:
                return SECOND;
            case MERGE_ITERATIONS_CHAIN:
                return CHAIN;
            default:
                throw new Error("Unknown Merge Iterations value");
        }
    }
    
    /**
     * Property editor for the <code>Anchor</code> type.
     * @see com.sun.glf.util.EnumPropertyEditorSupport
     */
    static public class MergeIterationsPropertyEditor extends EnumPropertyEditorSupport{
        /**
         * This method is intended for use when generating Java code to set
         * the value of the property.  It returns a fragment of Java code
         * that can be used to initialize a variable with the current property
         * value.
         */
        public String getJavaInitializationString() {
            MergeIterations val = (MergeIterations)getValue();
            if(val==null)
                return "null";
            switch(val.toInt()){
                case MERGE_ITERATIONS_ADDITIVE:
                    return "Additive";
                case MERGE_ITERATIONS_SUBTRACTIVE:
                    return "Subtractive";
                case MERGE_ITERATIONS_FIRST:
                    return "First";
                case MERGE_ITERATIONS_SECOND:
                    return "Second";
                case MERGE_ITERATIONS_CHAIN:
                    return "Chain";
                default:
                    throw new Error("Unknown Merge value");
            }
        }
        
        public MergeIterationsPropertyEditor(){
            super(enumValues);
        }
    }
    
    /**
     * Static initializer registers the editor with the property editor
     * manager
     */
    static {
        PropertyEditorManager.registerEditor(MergeIterations.class, MergeIterationsPropertyEditor.class);
    }
}
