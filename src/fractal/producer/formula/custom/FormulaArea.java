/*
 * FormulaArea.java
 *
 * Created on 26 July 2007, 22:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.formula.custom;

import fractal.producer.calc.ComplexNumber;
import fractal.producer.formula.Formula;
import fractal.producer.formula.builtin.Mandelbrot;

/**
 *
 * @author Owner
 */
public interface FormulaArea {
    public boolean contains(ComplexNumber p);
}
