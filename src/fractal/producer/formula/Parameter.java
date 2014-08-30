/*
 * Parameter.java
 *
 * Created on 14 February 2007, 21:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.formula;

import fractal.producer.calc.ComplexNumberFactory;

/**
 *
 * @author Owner
 */
public class Parameter {
    
    public static final int TYPE_DECIMAL = 0;
    public static final int TYPE_INTEGER = 1;
    public static final int TYPE_COMPLEX = 2;
    public static final int TYPE_STRING  = 3;
    private String name = "p";
    private String caption = "Parameter";
    private Object def = 1d;
    
    /** Creates a new instance of Parameter */
    public Parameter(String name,String caption,Object def) {
        this.name=name;
        this.caption=caption;
        this.def=def;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Object getDefault() {
        return def;
    }

    public void setDefault(Object def) {
        this.def = def;
    }
    public static Object parseDefault(String s, ComplexNumberFactory factory){
        return s;
    }
    
    
    
}
