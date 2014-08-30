/*
 * Parameters.java
 *
 * Created on 8 February 2007, 22:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer;

import fractal.producer.calc.BigMath;
import fractal.producer.calc.ComplexDoubleFactory;
import fractal.producer.calc.ComplexNumberFactory;
import fractal.producer.calc.NumberFactory;
import java.io.Serializable;
import java.math.MathContext;

/**
 *
 * @author Owner
 */
public class PrecisionContext implements Serializable {
    private ComplexNumberFactory complexFactory = null;
    private NumberFactory numberFactory = null;
    private MathContext mathContext = MathContext.DECIMAL64;
    /** Creates a new instance of Parameters */
    public PrecisionContext() {
        complexFactory = new ComplexDoubleFactory();
        numberFactory = new NumberFactory();
    }
    public PrecisionContext(ComplexNumberFactory factory,NumberFactory nFact) {
        this.setComplexNumberFactory(factory);
        this.setNumberFactory(nFact);
    }
    public PrecisionContext(int precision) {
        mathContext = new MathContext(precision);
        if( precision > BigMath.DOUBLE_DECIMAL_PLACES) {
           //setComplexNumberFactory()
        }
        else {
           setComplexNumberFactory(new ComplexDoubleFactory());
           setNumberFactory(new NumberFactory(mathContext));
        }
    }
    public ComplexNumberFactory getComplexNumberFactory() {
        return complexFactory;
    }
    public void setComplexNumberFactory(ComplexNumberFactory complexFactory) {
        this.complexFactory = complexFactory;
    }

    public NumberFactory getNumberFactory() {
        return numberFactory;
    }

    public void setNumberFactory(NumberFactory numberFactory) {
        this.numberFactory = numberFactory;
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
        this.numberFactory.setMathContext(mathContext);
    }
}
