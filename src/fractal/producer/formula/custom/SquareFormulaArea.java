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
public class SquareFormulaArea implements FormulaArea {
    private Formula formula = new Mandelbrot();
    private double x = 0d;
    private double y = 0d;
    private double width = 1.0d;
    private double height = 1.0d;
    
    public boolean contains(ComplexNumber p){
        double r = p.getReal().doubleValue();
        double i = p.getImag().doubleValue();
        if( r > x && r < (x+width) && i>y && i<(y+height) )
            return true;
        else return false;
    }
    
    public Formula getFormula() {
        return formula;
    }
    
    public void setFormula(Formula formula) {
        this.formula = formula;
    }
    
    public double getX() {
        return x;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getWidth() {
        return width;
    }
    
    public void setWidth(double width) {
        this.width = width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
}
