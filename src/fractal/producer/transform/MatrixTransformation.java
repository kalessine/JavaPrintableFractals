/*
 * MatrixTransformation.java
 *
 * Created on 10 February 2007, 21:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.transform;

import fractal.producer.calc.ComplexNumber;
import java.util.HashMap;

/**
 *
 * @author Owner
 */
public class MatrixTransformation implements Transform {
    
    /** Creates a new instance of MatrixTransformation */
    public MatrixTransformation() {
    }

    public ComplexNumber transform(ComplexNumber point,HashMap map){
     return point;
    }
      
    public Transform clone() { return this; }
}
