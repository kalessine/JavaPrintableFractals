/*
 * ComplexDoubleFactory.java
 *
 * Created on 8 February 2007, 22:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.calc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;

/**
 *
 * @author Owner
 */
public class ComplexDoubleFactory implements ComplexNumberFactory,Serializable {
    /** Creates a new instance of ComplexDoubleFactory */
    public ComplexDoubleFactory() {
    }
    public ComplexNumber createComplexNumber() {return new ComplexDouble();}
    public ComplexNumber createComplexNumber(Number r, Number i){
       return new ComplexDouble(r.doubleValue(),i.doubleValue());
    }
    public ComplexNumber parse(String s)throws ParseException {
       s = s.trim();
       if( s.charAt(0)!='(' ) throw new ParseException(s,0);
       s = s.substring(1,s.length());
       if( s.charAt(s.length()-1)!=')' ) throw new ParseException(s,s.length());
       s = s.substring(0,s.length()-1);
       String[] decimals = s.split(",");
       if( decimals.length!=2) throw new ParseException(s,1);
       Double r = Double.parseDouble(decimals[0]);
       Double i = Double.parseDouble(decimals[1]);
       return createComplexNumber(r,i);
    }
    public ComplexNumber parse(String s, int precision)throws ParseException {
       if( precision<=16) return parse(s);
       else {
           return null;
           /*
       s = s.trim();
       if( s.charAt(0)!='(' ) throw new ParseException(s,0);
       s = s.substring(1,s.length());
       if( s.charAt(s.length())!=')' ) throw new ParseException(s,s.length());
       s = s.substring(0,s.length()-1);
       String[] decimals = s.split(",");
       if( decimals.length!=2) throw new ParseException(s,1);
       MathContext mc = new MathContext(precision);
       BigDecimal r = new BigDecimal(decimals[0],mc);
       BigDecimal i = new BigDecimal(decimals[1],mc);
       return new ComplexArbitrary(r,i);
            **/
       }
    }
    public ComplexNumber parseDouble(String s)throws ParseException{
       return parse(s);
    }
    public ComplexNumber parseArbitrary(String s,int c)throws ParseException{
       return parse(s);
    }

}
