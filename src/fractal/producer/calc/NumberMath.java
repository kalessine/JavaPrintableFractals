/*
 * NumberMath.java
 *
 * Created on 8 February 2007, 22:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.calc;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author Owner
 */
public class NumberMath {
    
    /** Creates a new instance of NumberMath */
    private NumberMath() {
    }
    public static Number abs(Number n){
        if(n instanceof Double ) return Math.abs(n.doubleValue());
        else if( n instanceof BigDecimal ) return ((BigDecimal)n).abs();
        else throw new UnsupportedOperationException();
    }
    public static Number sqrt(Number n,MathContext context) {
        if( n instanceof Double ) return Math.sqrt(n.doubleValue());
        else if( n instanceof BigDecimal) return BigMath.sqrt((BigDecimal)n,context);
        else throw new UnsupportedOperationException();
    }
    public static int compareTo(Number a, Number b) {
        if( a instanceof Double && b instanceof Double ) {
            double a_ = a.doubleValue();
            double b_ = b.doubleValue();
            if( a_ == b_ ) return 0;
            if( a_ > b_) return 1;
            else return -1;
        }else if( a instanceof Integer && b instanceof Double ) {
            int a_ = a.intValue();
            double b_ = b.doubleValue();
            if( a_ == b_ ) return 0;
            if( a_ > b_) return 1;
            else return -1;
        }else if( a instanceof Double && b instanceof Integer ) {
            double a_ = a.doubleValue();
            int b_ = b.intValue();
            if( a_ == b_ ) return 0;
            if( a_ > b_) return 1;
            else return -1;
        } else if( a instanceof Integer && b instanceof Integer ) {
            int a_ = a.intValue();
            int b_ = b.intValue();
            if( a_ == b_ ) return 0;
            if( a_ > b_) return 1;
            else return -1;
        } else if( a instanceof BigDecimal && b instanceof BigDecimal ) {
            return ((BigDecimal)a).compareTo((BigDecimal)b);
        } else if( a instanceof BigDecimal && b instanceof Double ) {
            BigDecimal a_ = (BigDecimal)a;
            return a_.compareTo(new BigDecimal(b.doubleValue()));
        } else if( a instanceof Double && b instanceof BigDecimal ) {
            return new BigDecimal(a.doubleValue()).compareTo((BigDecimal)b);
        } else {
            throw new IllegalArgumentException("ints and floats not supported a="+a+", b="+b);
        }
    }
    public static Number cos(Number n,MathContext context) {
        if( n instanceof Double ) return Math.cos(n.doubleValue());
        else if( n instanceof BigDecimal ) {
            return BigMath.cos((BigDecimal)n,context);
        }
        throw new IllegalArgumentException("ints and floats not supported");
    }
    public static Number sin(Number n, MathContext context) {
        if( n instanceof Double ) return Math.sin(n.doubleValue());
        else if( n instanceof BigDecimal ) {
            return BigMath.sin((BigDecimal)n,context);
        }
        throw new IllegalArgumentException("ints and floats not supported");
    }
    public static Number multiply(Number a, Number b,MathContext context) {
        if( a instanceof Double && b instanceof Double ) {
            return a.doubleValue()*b.doubleValue();
        } else if( a instanceof BigDecimal && b instanceof BigDecimal ) {
            return ((BigDecimal)a).multiply((BigDecimal)b,context);
        }else if( a instanceof Double && b instanceof Integer) {
            return a.doubleValue()*b.intValue();
        } else if( a instanceof Integer && b instanceof Double ) {
            return a.intValue()*b.doubleValue();
        } else if( a instanceof BigDecimal && b instanceof Integer ) {
            return ((BigDecimal)a).multiply(new BigDecimal(b.intValue()));
        } else{
            System.out.println("a="+a);
            System.out.println("b="+b);
            throw new IllegalArgumentException("ints and floats not supported");
        }
    }
    public static Number add(Number a, Number b,MathContext context) {
        if( a instanceof Double && b instanceof Double ) {
            return a.doubleValue()+b.doubleValue();
        } else if( a instanceof BigDecimal && b instanceof BigDecimal ) {
            return ((BigDecimal)a).add((BigDecimal)b,context);
        }else if( a instanceof Double && b instanceof Integer ) {
            return a.doubleValue() + b.intValue();
        } else if( a instanceof Integer && b instanceof Double ) {
            return a.intValue() + b.doubleValue();
        } else if( a instanceof Integer && b instanceof Integer ) {
            return a.intValue() + b.intValue();
        }else throw new IllegalArgumentException("ints and floats not supported");
    }
    public static Number subtract(Number a, Number b,MathContext context) {
        if( a instanceof Double && b instanceof Double ) {
            return a.doubleValue()-b.doubleValue();
        } else if( a instanceof BigDecimal && b instanceof BigDecimal ) {
            return ((BigDecimal)a).subtract((BigDecimal)b,context);
        }else if( a instanceof Double && b instanceof Integer ) {
            return a.doubleValue() - b.intValue();
        } else if( a instanceof Integer && b instanceof Double ) {
            return a.intValue() - b.doubleValue();
        } else if( a instanceof Integer && b instanceof Integer ) {
            return a.intValue() - b.intValue();
        }else throw new IllegalArgumentException("ints and floats not supported");
    }
    public static Number mod(Number a, Number b,MathContext context) {
        if( a instanceof Double && b instanceof Double ) {
            return a.doubleValue()%b.doubleValue();
        } else if( a instanceof BigDecimal && b instanceof BigDecimal ) {
            return ((BigDecimal)a).remainder((BigDecimal)b,context);
        }else throw new IllegalArgumentException("ints and floats not supported");
    }
    public static Number divide(Number a, Number b,MathContext context) {
        if( a instanceof Double && b instanceof Double ) {
            return a.doubleValue()/b.doubleValue();
        } else if( a instanceof Double && b instanceof Integer ) {
            return a.doubleValue()/b.intValue();
        } else if( a instanceof BigDecimal && b instanceof BigDecimal ) {
            return ((BigDecimal)a).divide((BigDecimal)b,context);
        } else if( a instanceof BigDecimal && b instanceof Integer) {
            return ((BigDecimal)a).divide(new BigDecimal(b.intValue()),context);
        } else if( a instanceof Integer && b instanceof BigDecimal) {
            return new BigDecimal(a.intValue()).divide((BigDecimal)b,context);
        } else if( a instanceof BigDecimal && b instanceof Double) {
            return ((BigDecimal)a).divide(new BigDecimal(b.doubleValue()));
        } else{
            System.out.println("a="+a+" "+a.getClass());
            System.out.println("b="+b+" "+b.getClass());
            throw new IllegalArgumentException("ints and floats not supported");
        }
    }
    public static MathContext getDefaultMathContext() {
        return MathContext.DECIMAL64;
    }
    public static Number toBigDecimal(Number n,MathContext context) {
        if( n instanceof BigDecimal) return n;
        else return new BigDecimal(n.doubleValue(),context);
    }
    public static Number toDouble(Number n) {
        return n.doubleValue();
    }
}
