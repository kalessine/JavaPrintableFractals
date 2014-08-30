/*
 * BigMath.java
 *
 * Created on 16 January 2007, 19:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.calc;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
/**
 *
 * @author Owner
 */
public class BigMath {
    
    public static void main(String args[]) {
        MathContext context = new MathContext(100);
        System.out.println("---------------------------");
        System.out.println("--   Big Math Test Suite --");
        System.out.println("---------------------------");
        System.out.println(" Square Root Of 2");
        System.out.println("Math.sqrt(2.0d)="+Math.sqrt(2.0d));
        System.out.println("BigMath.sqrt(2.0d)="+BigMath.sqrt(new BigDecimal(2.0d,context),context));
        System.out.println("Math.cos(1.5d)="+Math.cos(1.5d));
        System.out.println("BigMath.cos(1.5d)="+BigMath.cos(new BigDecimal(1.5d,context),context));
        System.out.println("Math.sin(31.2d)="+Math.sin(31.2d));
        System.out.println("BigMath.sin(31.2d)="+BigMath.sin(new BigDecimal(31.2d),context));
        System.out.println("Math.PI="+Math.PI);
        System.out.println("BigMath.pi="+pi(context));
    }

    public static final int DOUBLE_DECIMAL_PLACES = 16;
    
    public static final BigDecimal NEGATIVE_ONE = new BigDecimal(-1);
    public static final BigDecimal ZERO = new BigDecimal(0);
    public static final BigDecimal HALF = new BigDecimal(0.5d);
    public static final BigDecimal ONE = new BigDecimal(1);
    public static final BigDecimal TWO = new BigDecimal(2);
    public static final BigDecimal THREE = new BigDecimal(3);
    public static final BigDecimal FOUR = new BigDecimal(4);
    public static final BigDecimal SIXTEEN = new BigDecimal(16);
    public static final BigDecimal ONE_ON_FIVE = new BigDecimal(0.2d);
    public static final BigDecimal ONE_HUNDRED_AND_EIGHTY = new BigDecimal(180d);
    public static BigDecimal PI = new BigDecimal(Math.PI);
    
    public static CosService cosService = null;
    private static BigSquareRoot SQUARE_ROOT = new BigSquareRoot();
    
    private BigMath() {
        
        
    }
    public static BigDecimal sqr(BigDecimal i,MathContext context) {
        return i.multiply(i,context);
    }
    public static BigDecimal sqrt(BigDecimal i,MathContext context) {
        BigDecimal sqrt;
        synchronized(SQUARE_ROOT){
            SQUARE_ROOT.setScale(context.getPrecision() / 2);
            sqrt = SQUARE_ROOT.get(i);
        }
        return sqrt;
    }
    public static BigDecimal sqrt(BigInteger i,MathContext context) {
        BigDecimal sqrt;
        synchronized(SQUARE_ROOT){
            SQUARE_ROOT.setScale(context.getPrecision() / 2);
            sqrt = SQUARE_ROOT.get(i);
        }
        return sqrt;
    }
    public static BigDecimal abs(BigDecimal i) {
        return i.abs();
    }
    public static BigInteger abs(BigInteger i) {
        return i.abs();
    }
    public static BigDecimal cos(BigDecimal b, MathContext context) {
        if( cosService == null  ) cosService = new CosService(context.getPrecision());
        if( cosService.getPrecision()<context.getPrecision()) cosService = new CosService(context.getPrecision());
        //b=b.divide(ONE_HUNDRED_AND_EIGHTY);
        //b=b.multiply(pi(context));
        return cosService.cos(b);
    }
    // Code Grabbed From http://www.jensign.com/JavaScience/www/bigpi/index.html
    public static BigDecimal pi(MathContext context) {
        if(PI.precision()>context.getPrecision()) return PI;
        int tscale = context.getPrecision();
        
        BigDecimal one = BigMath.ONE;
        BigDecimal four = BigMath.FOUR;
        BigDecimal sixteen = BigMath.SIXTEEN;
        BigDecimal oneby5 = ONE_ON_FIVE;
        BigDecimal oneby239 = one.divide(new BigDecimal("239"), tscale, BigDecimal.ROUND_DOWN) ;
        
        BigDecimal atn15=null, atn239=null, xsq=null, powercum=null, bigpi=null;
        
        //calculate arctan(1/5) to tscale digits of precision:
        // Based on log10(1/5) need about 1.5*tscale maximum exponent.
        
        atn15 = oneby5 ;      // initialize to first term of arctan series.
        powercum = oneby5;   // start with first power.
        xsq = oneby5.multiply(oneby5) ;
        
        for (int i=3; i<=(3*tscale/2); i+=4) {
            powercum = powercum.multiply(xsq) ;
            atn15 = atn15.subtract(powercum.divide(new BigDecimal(String.valueOf(i)), tscale, BigDecimal.ROUND_DOWN));
            powercum = powercum.multiply(xsq) ;
            atn15 = atn15.add(powercum.divide(new BigDecimal(String.valueOf(i+2)), tscale, BigDecimal.ROUND_DOWN));
            atn15 = atn15.setScale(tscale, BigDecimal.ROUND_DOWN) ;
        }
        
        //calculate arctan(1/239) to tscale digits of precision:
        // Based on log10(1/239) need about 0.5*tscale maximum exponent.
        atn239 = oneby239 ;      // initialize to first term of arctan series.
        powercum = oneby239;   // start with first power.
        xsq = oneby239.multiply(oneby239) ;
        
        for (int i=3; i<=tscale/2; i+=4) {
            powercum = (powercum.multiply(xsq)).setScale(tscale, BigDecimal.ROUND_DOWN) ;
            atn239 = atn239.subtract(powercum.divide(new BigDecimal(String.valueOf(i)), BigDecimal.ROUND_DOWN));
            powercum = (powercum.multiply(xsq)).setScale(tscale, BigDecimal.ROUND_DOWN) ;
            atn239 = atn239.add(powercum.divide(new BigDecimal(String.valueOf(i+2)),  BigDecimal.ROUND_DOWN));
            atn239 = atn239.setScale(tscale, BigDecimal.ROUND_DOWN) ;
        }
        
        
        bigpi = sixteen.multiply(atn15) ;
        bigpi = bigpi.subtract(four.multiply(atn239)) ;
        PI=bigpi;
        return bigpi;
    }
    // This method adapted from http://uvsc.freshsources.com/sine.pdf
    public static BigDecimal sin(BigDecimal x, MathContext context) {
        int k = context.getPrecision()/3;
        int n = x.divide(pi(context),context).add(new BigDecimal(0.5*sign(x),context)).intValue();
        x=x.subtract(pi(context).multiply(new BigDecimal(n),context));
        BigDecimal num = x;
        BigDecimal den = BigMath.ONE;
        BigDecimal sum = x;
        int fact = 1; // The denominator contains fact!
        for (int i = 0; i < k; ++i) {
            num = BigMath.ZERO.subtract(num,context).multiply(x,context).multiply(x,context);
            den = den.multiply(new BigDecimal(++fact));
            den = den.multiply(new BigDecimal(++fact));
            sum = sum.add(num.divide(den,context),context);
        }
        return (n%2)!=0 ? BigMath.ZERO.subtract(sum) : sum;
    }
    
    public static int sign(BigDecimal x) {
        int i = x.compareTo(BigMath.ZERO);
        if( i== -1 ) return -1;
        else if( i==0 ) return 1;
        else return 1;
    }
    public static BigDecimal pow(BigDecimal x, int n,MathContext context) {
        BigDecimal result = BigDecimal.ONE;
        while(n!=0){
            if(n%2!=0){
                result = result.multiply(x,context);
                n = n-1;
            } else{
                x = x.multiply(x,context);
                n = n/2;
            }
        }
        return result;
    }
}

