/*
 * OrbitTrap.java
 *
 * Created on 7 March 2007, 18:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.orbittrap;

import fractal.producer.calc.ComplexNumber;
import fractal.producer.colour.Colouring;
import java.util.HashMap;

/**
 *
 * @author Owner
 */
public interface OrbitTrap extends Colouring {
    public static final String ORBIT_INDEX = "orbitindex";
    public void init(HashMap map);
    public void hit(HashMap map,Number iterations);
    public Object getTrapValue();
}
