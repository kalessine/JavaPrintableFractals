/////////////////////////
// formula01

//package emberj.formulas;

import Complex.*; // import the Complex class
import emberj.*;

/**
 *
 * <p>Title: EmberJ2</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ktaza.com</p>
 *
 * @author Stephen C. Ferguson
 * @version 1.0
 */

public class formula01 extends FormulaComponent
{
  Complex z, c;

  public formula01()
  {
  }

  public double init(Complex z, Complex c)
  {
    this.c = c;
    this.z = z;
    return this.z.real() * this.z.real() + this.z.imag() * this.z.imag();
  }

  public Complex formula()
  {
    // z=z*z+c;
    z.assign(z.mult(z).mult(c).add(c));
    return z;
  }
}





