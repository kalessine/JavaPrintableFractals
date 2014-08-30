/*
 * NumberFactory.java
 *
 * Created on 8 February 2007, 23:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.calc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author Owner
 */
public class NumberFactory implements Serializable {
    
    /** Creates a new instance of NumberFactory */
    private MathContext mathContext = MathContext.DECIMAL64;
    public NumberFactory() {
    }
    public NumberFactory(MathContext math) {
        this.setMathContext(math);
    }
    public Number getNumber(int i) {
        if( getMathContext().getPrecision()> 16) return bigDecimal((double)i);
        else return i;
    }
    public Number getNumber(double d) {
        if( getMathContext().getPrecision()>16 ) return bigDecimal(d);
        else return d;
    }
    private BigDecimal bigDecimal(double d) {
       if( d == 0) return BigMath.ZERO;
       else if( d == 1) return BigMath.ONE;
       else if( d == 2) return BigMath.TWO;
       else if( d == 3) return BigMath.THREE;
       else return new BigDecimal(d, getMathContext());
    }
    
    public MathContext getMathContext() {
        return mathContext;
    }

    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
    }
    public Number parse(String s) {
        if( getMathContext().getPrecision()>BigMath.DOUBLE_DECIMAL_PLACES) {
           return new BigDecimal(s);
        }
        else return Double.parseDouble(s);
    }
}
