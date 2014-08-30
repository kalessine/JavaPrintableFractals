package fractal.producer.calc.old;
/*
 This Complex Number Class was shamelessly ripped from
 http://www.jamesh.id.au/fractals/
 */
public final class ComplexDouble {
  double r, i;
  
  public ComplexDouble ()                        { r = 0.0; i = 0.0; }
  public ComplexDouble (double x, double y)      { r = x;   i = y;   }
  public ComplexDouble (ComplexDouble cplx)      { r = cplx.real(); i = cplx.imag(); }
  
  public void set (double x, double y)   { r = x;   i = y;   }
  public void set (ComplexDouble cn)   { r = cn.real();   i = cn.imag();   }
  public double real ()                  { return r; }
  public double imag ()                  { return i; }  
  public double modsq ()                 { return r * r + i * i; }
  
  public ComplexDouble add (ComplexDouble p)     { r += p.real(); i += p.imag(); return this; }
  public ComplexDouble add (double p)    { r += p; return this; }
  public ComplexDouble add (double rl,double im)    { r += rl;i+= im; return this; }
  public ComplexDouble add (int p)       { r += p; return this; }
  public ComplexDouble subtr (ComplexDouble p)   { r -= p.real(); i -= p.imag(); return this; }
  public ComplexDouble subtr (double p)  { r -= p; return this; }
  public ComplexDouble subtr (int p)     { r -= p; return this; }
  public ComplexDouble mult (double p)   { r *= p; i *= p; return this; }
  public ComplexDouble mult (int p)      { r *= p; i *= p; return this; }
  public ComplexDouble mult (ComplexDouble p) {
    r =  r * p.real() - i * p.imag();
    i =  i * p.real() + r * p.imag();
    return this;
  }
  public ComplexDouble divid (ComplexDouble p) {
    double tmp = p.modsq();
    r = (r * p.real() + i * p.imag()) / tmp;
    i = (i * p.real() - r * p.imag()) / tmp;
    return this;
  }
  
  public ComplexDouble sqr() {
    set(r * r - i * i, 2 * r * i);
    return this;
  }
  
  public ComplexDouble cube() {
    double r2 = r*r, i2 = i*i;
    set(r * (r2 - 3 * i2), i * (3 * r2 - i2));
    return this;
  }

  public ComplexDouble exp() {
    double e1 = Math.exp(r);
    
    set(e1 * Math.cos(i), e1 * Math.sin(i));
    return this;
  }
  
  public ComplexDouble sin() {
    double e1 = Math.exp(i), e2 = 1/e1;
    
    set(Math.sin(r) * (e1 + e2) / 2, Math.cos(r) * (e1 - e2) / 2);
    return this;
  }

  public ComplexDouble cos() {
    double e1 = Math.exp(i), e2 = 1/e1;
    
    set(Math.cos(r) * (e1 + e2) / 2, -Math.sin(r) * (e1 - e2) / 2);
    return this;
  }

  public ComplexDouble add (ComplexDouble p1, ComplexDouble p2)   { r = p1.real() + p2.real();
                                           i = p1.imag() + p2.imag();
                                           return this; }
  public ComplexDouble subtr (ComplexDouble p1, ComplexDouble p2) { r = p1.real() - p2.real();
                                           i = p1.imag() - p2.imag();
                                           return this; }
  public ComplexDouble mult (ComplexDouble p1, ComplexDouble p2) {
    r =  p1.real() * p2.real() - p1.imag() * p2.imag();
    i =  p1.imag() * p2.real() + p1.real() * p2.imag();
    return this;
  }
  public ComplexDouble divid (ComplexDouble p1, ComplexDouble p2) {
    double tmp = p2.modsq();
    r = (p1.real() * p2.real() + p1.imag() * p2.imag()) / tmp;
    i = (p1.imag() * p2.real() - p1.real() * p2.imag()) / tmp;
    return this;
  }

  public ComplexDouble sqr(ComplexDouble p) {
    r = p.real() * p.real() - p.imag() * p.imag();
    i = 2 * p.real() * p.imag();
    return this;
  }
  public ComplexDouble cube(ComplexDouble p) {
    double r2 = p.real() * p.real(), i2 = p.imag() * p.imag();
    
    set(p.real() * (r2 - 3 * i2), p.imag() * (3 * r2 - i2));
    return this;
  }

  public ComplexDouble exp(ComplexDouble p) {
    double e1 = Math.exp(p.real());
    
    set(e1 * Math.cos(p.imag()), e1 * Math.sin(p.imag()));
    return this;
  }
  
  public ComplexDouble sin(ComplexDouble p) {
    double e1 = Math.exp(p.imag()), e2 = 1/e1;
    set(Math.sin(p.real()) * (e1 + e2)/2, Math.cos(p.real()) * (e1 - e2)/2);
    return this;
  }

  public ComplexDouble cos(ComplexDouble p) {
    double e1 = Math.exp(p.imag()), e2 = 1/e1;
    
    set(Math.cos(p.real()) * (e1 + e2)/2, -Math.sin(p.real()) * (e1 - e2)/2);
    return this;
  }

}
