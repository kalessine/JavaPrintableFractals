/*
 * Transform.java
 *
 * Created on 8 February 2007, 20:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.transform;

import fractal.producer.calc.ComplexNumber;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Owner
 */
public interface Transform extends Serializable {
    public ComplexNumber transform(ComplexNumber point,HashMap map);
    public Transform clone();
}
