package fractal.producer.calc;
import java.util.List;
import java.util.Vector;
import java.math.BigDecimal;

public class CosService {

    private List _cosFactors;
    private int _precision;

    public CosService(int precision) {
	_precision = precision;
	_cosFactors = new Vector();
	BigDecimal one = new BigDecimal(1.0);
	BigDecimal stopWhen = one.movePointLeft(precision+5);
	BigDecimal divWith = new BigDecimal(2.0);
	BigDecimal inc = new BigDecimal(2.0);
	BigDecimal factor = null;
	do {
	    factor = one.divide(divWith, precision+5,
				BigDecimal.ROUND_HALF_UP);
	    _cosFactors.add(factor);
	    inc = inc.add(one);
	    divWith = divWith.multiply(inc);
	    inc = inc.add(one);
	    divWith = divWith.multiply(inc);
	} while (factor.compareTo(stopWhen)>0);
    }

    public BigDecimal cos(BigDecimal x) {
	BigDecimal res = new BigDecimal(1.0);
	BigDecimal xn = x.multiply(x);
	for (int i=0;i<_cosFactors.size();i++) {
	    BigDecimal factor = (BigDecimal)_cosFactors.get(i);
	    factor = factor.multiply(xn);
	    if (i%2==0) {
		factor = factor.negate();
	    }
	    res = res.add(factor);
	    xn = xn.multiply(x);
	    xn = xn.multiply(x);
	    xn = xn.setScale(_precision+5, BigDecimal.ROUND_HALF_UP);
	}
	return res.setScale(_precision, BigDecimal.ROUND_HALF_UP);
    }

    
    public static void main(String[] args) {
	CosService ts = new CosService(30);
	System.out.println("java.lang.Math.cos(0.5)="+Math.cos(0.5));
	System.out.println("this.cos(0.5)="+ts.cos(new BigDecimal(0.5)));
    }
    public int getPrecision() { return _precision; }
}
