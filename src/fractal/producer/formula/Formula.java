/*
 * Formula.java
 *
 * Created on November 7, 2006, 5:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.formula;

import fractal.producer.Coordinates;
import fractal.producer.calc.ComplexNumberFactory;
import java.io.Serializable;
import java.util.HashMap;

public interface Formula extends Serializable {
    public static final String Z = "z";
    public static final String C = "c";
    public static final String PIXEL = "pixel";
    public static final String LASTZ = "lastz";
    public static final String CENTER = "center";
    public static final String MAX_ITERATION = "maxIteration";
    public Coordinates getDefaultCoordinates();
    public void init(HashMap map,ComplexNumberFactory factory);
    public void loop(HashMap map);
    public boolean bailout(HashMap map);
    //public Result getResult(ComplexNumber pixel,HashMap map,PrecisionContext params);
    public Formula clone();
}
