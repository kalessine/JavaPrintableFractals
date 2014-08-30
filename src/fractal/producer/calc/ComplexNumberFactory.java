/*
 * ComplexNumberFactory.java
 *
 * Created on 8 February 2007, 22:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.calc;

import java.text.ParseException;

/**
 *
 * @author Owner
 */
public interface ComplexNumberFactory {
    public ComplexNumber createComplexNumber();
    public ComplexNumber createComplexNumber(Number r, Number i);
    public ComplexNumber parse(String s)throws ParseException;
    public ComplexNumber parse(String s,int precision)throws ParseException;
}
