/*
 * M.java
 *
 * Created on 11 February 2007, 22:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.calc;

import java.math.MathContext;

/**
 *
 * @author Owner
 */
public class M {
    
    /** Creates a new instance of M */
    private M() {
    }
    public static Number abs(Number n) {
        return NumberMath.abs(n);
    }
    public static Number sqrt(Number n,MathContext context) {
        return NumberMath.sqrt(n,context);
    }
    public static int compareTo(Number a, Number b) {
        return NumberMath.compareTo(a,b);
    }
    public static Number cos(Number n,MathContext context) {
        return NumberMath.cos(n,context);
    }
    public static Number sin(Number n, MathContext context) {
        return NumberMath.sin(n,context);
    }
    public static Number mult(Number a, Number b,MathContext context) {
        return NumberMath.multiply(a,b,context);
    }
    public static Number multiply(Number a, Number b,MathContext context) {
        return NumberMath.multiply(a,b,context);
    }
    public static Number add(Number a, Number b,MathContext context) {
        return NumberMath.add(a,b,context);
    }
    public static Number sub(Number a, Number b,MathContext context) {
        return NumberMath.subtract(a,b,context);
    }
    public static Number subtract(Number a, Number b,MathContext context) {
        return NumberMath.subtract(a,b,context);
    }
    public static Number mod(Number a, Number b,MathContext context) {
        return NumberMath.mod(a,b,context);
    }
    public static Number div(Number a, Number b,MathContext context) {
        return NumberMath.divide(a,b,context);
    }
    public static Number divide(Number a, Number b,MathContext context) {
        return NumberMath.divide(a,b,context);
    }
    public static Number toBigDecimal(Number n,MathContext context) {
       return NumberMath.toBigDecimal(n,context);
    }
    public static Number toDouble(Number n) {
        return NumberMath.toDouble(n);
    }
}
