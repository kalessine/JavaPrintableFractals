/*
 * TransformStore.java
 *
 * Created on 26 February 2007, 10:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.formula;

import fractal.producer.formula.builtin.ComplexPower;
import fractal.producer.formula.builtin.CubicMandelMixOnMagnitude;
import fractal.producer.formula.builtin.CubicMandelbrot;
import fractal.producer.formula.builtin.Evert;
import fractal.producer.formula.builtin.Julia;
import fractal.producer.formula.builtin.JuliaComplexPower;
import fractal.producer.formula.builtin.MandelCubicMixOnMagnitude;
import fractal.producer.formula.builtin.MandelQuarticMixOnMagnitude;
import fractal.producer.formula.builtin.Mandelbrot;
import fractal.producer.formula.builtin.OneOverZNPlusC;
import fractal.producer.formula.builtin.QuarticMandelMixOnMagnitude;
import fractal.producer.formula.builtin.QuarticMandelbrot;
import fractal.producer.formula.builtin.ZSquaredFlipPlusC;
import fractal.producer.formula.builtin.ZSquaredPlusCFlip;
import fractal.producer.formula.builtin.ZSquaredPlusCFlipReal;
import fractal.producer.formula.builtin.ZSquaredPlusZ;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Owner
 */
public class FormulaStore {
    static List FORMULAS = new ArrayList(0);
    /** Creates a new instance of TransformStore */
    private FormulaStore() {
    }
    public static List getFormulas() { return FORMULAS; }
    public static int getSize() { return FORMULAS.size(); }
    public static Formula getFormula(int id) { return (Formula)FORMULAS.get(id); }
    public static void loadFormula(Formula f) {
        FORMULAS.add(f);
    }
    public static void unloadFormula(Formula f) {
        FORMULAS.remove(f);
    }
    static{
        FORMULAS.add(new Mandelbrot());
        FORMULAS.add(new CubicMandelbrot());
        FORMULAS.add(new QuarticMandelbrot());
        FORMULAS.add(new ZSquaredFlipPlusC());
        FORMULAS.add(new ZSquaredPlusCFlip());
        FORMULAS.add(new ZSquaredPlusCFlipReal());
        FORMULAS.add(new ZSquaredPlusZ());
        FORMULAS.add(new Evert());
        FORMULAS.add(new Julia());
        FORMULAS.add(new JuliaComplexPower());
        FORMULAS.add(new MandelCubicMixOnMagnitude());
        FORMULAS.add(new CubicMandelMixOnMagnitude());
        FORMULAS.add(new MandelQuarticMixOnMagnitude());
        FORMULAS.add(new QuarticMandelMixOnMagnitude());
        FORMULAS.add(new ComplexPower());
        FORMULAS.add(new OneOverZNPlusC());
    }
}
