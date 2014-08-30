package fractal.producer.calc;
/*
 This Complex Number Class was shamelessly ripped from
 http://www.jamesh.id.au/fractals/
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import fractal.producer.calc.BigMath;
public final class ComplexArbitrary {

  MathContext context = MathContext.DECIMAL128;

  BigDecimal r;
  BigDecimal i;
  
  public ComplexArbitrary ()                        { r = new BigDecimal(0d); i =new BigDecimal(0d); }
  public ComplexArbitrary (MathContext context)     { r = new BigDecimal(0d); i =new BigDecimal(0d); this.context=context; }
  public ComplexArbitrary (BigDecimal x, BigDecimal y)  { r = x;   i = y;   }
  public ComplexArbitrary (BigDecimal x, BigDecimal y,MathContext context)  { r = x;   i = y; this.context=context;  }
  public ComplexArbitrary (ComplexArbitrary cplx)      { r = cplx.real(); i = cplx.imag(); }
  
  public void set (BigDecimal x, BigDecimal y)   { r = x;   i = y;   }
  public void set (ComplexArbitrary cn)   { r = cn.real();   i = cn.imag();   }
  public BigDecimal real ()                  { return r; }
  public BigDecimal imag ()                  { return i; }  
  public BigDecimal modsq ()                 { 
      return r.multiply(r,context).add(i.multiply(i,context),context); 
  }
  
  public ComplexArbitrary add (ComplexArbitrary p)     { r=r.add(p.real(),context); i=i.add(p.imag(),context); return this; }
  public ComplexArbitrary add (BigDecimal p)    { r=r.add(p,context); return this; }
  public ComplexArbitrary add (BigDecimal rl,BigDecimal im)    { r=r.add(rl,context);i=i.add(im,context); return this; }
  public ComplexArbitrary add (int p)       { r=r.add(new BigDecimal(p),context); return this; }
  public ComplexArbitrary subtr (ComplexArbitrary p)   { r=r.subtract(p.real(),context); i=i.subtract(p.imag(),context); return this; }
  public ComplexArbitrary subtr (double p)  { r=r.subtract(new BigDecimal(p),context); return this; }
  public ComplexArbitrary subtr (int p)     { r=r.subtract(new BigDecimal(p),context); return this; }
  public ComplexArbitrary mult (double p)   { r=r.multiply(new BigDecimal(p),context); i=i.multiply(new BigDecimal(p),context); return this; }
  public ComplexArbitrary mult (BigDecimal p)   { r=r.multiply(p,context); i=i.multiply(p,context); return this; }
  public ComplexArbitrary mult (int p)      { r=r.multiply(new BigDecimal(p),context); i=i.multiply(new BigDecimal(p),context); return this; }
  public ComplexArbitrary mult (ComplexArbitrary p) {
    r =  r.multiply(p.real(),context).subtract(i.multiply(p.imag(),context),context);
    i =  i.multiply(p.real(),context).add(r.multiply(p.imag(),context),context);
    return this;
  }
  public ComplexArbitrary divid (ComplexArbitrary p) {
    BigDecimal tmp = p.modsq();
    r = r.multiply(p.real(),context).add(i.multiply(p.imag(),context),context).divide(tmp,context);
    i = i.multiply(p.real(),context).subtract(r.multiply(p.imag(),context),context).divide(tmp,context);
    return this;
  }
  
  public ComplexArbitrary sqr() {
    set(r.multiply(r,context).subtract(i.multiply(i),context), r.multiply(i,context).multiply(new BigDecimal(2),context));
    return this;
  }
  
  public ComplexArbitrary cube() {
    BigDecimal r2 = r.multiply(r,context), i2 = i.multiply(i,context);
    set(r.multiply(r2.subtract(i2.multiply(new BigDecimal(3),context),context),context), 
            i.multiply(r2.multiply(new BigDecimal(3),context).subtract(i2,context),context)
            );
    return this;
  }
  public ComplexArbitrary flip(){
      return new ComplexArbitrary(i,r);
  }
  
/*// Need to write own exp function i think..  :(
  public ComplexArbitrary exp() {
    double e1 = Math.exp(r);
    set(e1 * Math.cos(i), e1 * Math.sin(i));
    return this;
  }
 **/
  /*
  public ComplexNumber sin() {
    double e1 = Math.exp(i), e2 = 1/e1;
    
    set(Math.sin(r) * (e1 + e2) / 2, Math.cos(r) * (e1 - e2) / 2);
    return this;
  }

  public ComplexNumber cos() {
    double e1 = Math.exp(i), e2 = 1/e1;
    
    set(Math.cos(r) * (e1 + e2) / 2, -Math.sin(r) * (e1 - e2) / 2);
    return this;
  }
*/
  /*
  public ComplexNumber add (ComplexNumber p1, ComplexNumber p2)   { r = p1.real() + p2.real();
                                           i = p1.imag() + p2.imag();
                                           return this; }
   *
   */
  /*
  public ComplexNumber subtr (ComplexNumber p1, ComplexNumber p2) { r = p1.real() - p2.real();
                                           i = p1.imag() - p2.imag();
                                           return this; }
   **/
  /*
  public ComplexNumber mult (ComplexNumber p1, ComplexNumber p2) {
    r =  p1.real() * p2.real() - p1.imag() * p2.imag();
    i =  p1.imag() * p2.real() + p1.real() * p2.imag();
    return this;
  }
   **/
  /*
  public ComplexNumber divid (ComplexNumber p1, ComplexNumber p2) {
    double tmp = p2.modsq();
    r = (p1.real() * p2.real() + p1.imag() * p2.imag()) / tmp;
    i = (p1.imag() * p2.real() - p1.real() * p2.imag()) / tmp;
    return this;
  }
*/
  /*
  public ComplexNumber sqr(ComplexNumber p) {
    r = p.real() * p.real() - p.imag() * p.imag();
    i = 2 * p.real() * p.imag();
    return this;
  }
   **/
  /*
  public ComplexNumber cube(ComplexNumber p) {
    double r2 = p.real() * p.real(), i2 = p.imag() * p.imag();
    
    set(p.real() * (r2 - 3 * i2), p.imag() * (3 * r2 - i2));
    return this;
  }
*/
  /*
  public ComplexNumber exp(ComplexNumber p) {
    double e1 = Math.exp(p.real());
    
    set(e1 * Math.cos(p.imag()), e1 * Math.sin(p.imag()));
    return this;
  }
  */
  /*
  public ComplexNumber sin(ComplexNumber p) {
    double e1 = Math.exp(p.imag()), e2 = 1/e1;
    
    set(Math.sin(p.real()) * (e1 + e2)/2, Math.cos(p.real()) * (e1 - e2)/2);
    return this;
  }
*/
  /*
  public ComplexNumber cos(ComplexNumber p) {
    double e1 = Math.exp(p.imag()), e2 = 1/e1;
    
    set(Math.cos(p.real()) * (e1 + e2)/2, -Math.sin(p.real()) * (e1 - e2)/2);
    return this;
  }
*/
  public int getPrecision() {
      return context.getPrecision();
  }
}
