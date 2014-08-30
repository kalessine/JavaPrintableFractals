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
public class CircleFormulaArea extends SquareFormulaArea {
    public boolean contains(ComplexNumber p){
        if( super.contains(p)) return _contains(p);
        else return false;
    }
    public boolean _contains(ComplexNumber p) {
        double r = p.getReal().doubleValue();
        double i = p.getImag().doubleValue();
        double cx = getX()+(getWidth()/2);
        double cy = getY()+(getHeight()/2);
        double dx = r-cx;
        double dy = i-cy;
        double d = Math.sqrt(dx*dx+dy*dy);
        if( d < getWidth()/2 ) return true;
        else return false;
    }
}
