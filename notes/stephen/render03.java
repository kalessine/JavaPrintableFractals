/////////////////////////
// render01

package emberj.rendering;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import emberj.*;

public class render03
  extends RenderingComponent
{
  Mandelbrot m;
  double temp;

  private double delta;

  public render03()
  {
    //JOptionPane.showMessageDialog( null , "render01 constructor, ..." );
  }

  public void init(Mandelbrot M)
  {
    m = M;
    //JOptionPane.showMessageDialog( null , "render01 init, ..." );
  }

  public boolean filter()
  {
    delta = Math.abs(m.zsqr1 - m.zsqr0);
    delta *= delta;

    //JOptionPane.showMessageDialog( null , "render01 filter, ..." );

    //zsqrX = zr*zr;
    //zsqrY = zi*zi;

    //zsqrX = (zr*zr)/Math.abs(zi);
    //zsqrY = (zi*zi)/Math.abs(zr);

    //zsqrX = (zr*zr)/Math.abs(zi)+Bailout;
    //zsqrY = (zi*zi)/Math.abs(zr)+Bailout;

    //zsqrX = Bailout+Math.sin(zr*zr*100);
    //zsqrY = Bailout+Math.sin(zi*zi*100);

    //zsqrX = (zsqrX + Math.abs(zi*zi*100))/2;
    //zsqrY = (zsqrY + Math.abs(zr*zr*100))/2;

    //zsqrX = m.zr*m.zr+Bailout;
    //zsqrY = m.zi*m.zi+Bailout;

    //zsqrX = m.zr+Bailout;
    //zsqrY = m.zi+Bailout;

    //zsqrX = Math.abs(zsqrX-zsqrCX);
    //zsqrY = Math.abs(zsqrY-zsqrCY);

    //zsqrX = Math.abs(Math.sin(zsqrX*100));
    //zsqrY = Math.abs(Math.sin(zsqrY*100));

    //if (minX > zsqrX)
    //minX = zsqrX;

    //if (minY > zsqrY)
    //minY = zsqrY;

    //zsqr0 = zsqrX + zsqrY;

    //if ((Math.abs(zsqr0-zsqr1) < zerotol) || (zsqr0 > Bailout))

    //if ((Math.abs(zsqr0-zsqr1) < zerotol) ||
    //      (zsqrX/(zsqrY) > Bailout) ||
    //    (zsqrY/(zsqrX) > Bailout))  // biomorph method

    //if ((zsqrX/(Bailout+zsqrY) > Bailout) ||
    //  (zsqrY/(Bailout+zsqrX) > Bailout))  // biomorph by division method

    //if ((zsqrX > Bailout) ||
    //	  (zsqrY > Bailout))
    //(zsqrX < zerotol) ||
    //(zsqrY < zerotol))    // biomorph method

    //if ((zsqrX+zsqrY) > Bailout)
    //break;

    //if ((Math.abs(zsqr0-zsqr1) < zerotol))
    //break;

    //minX = minX + zsqrX;
    //minY = minY + zsqrY;

    //zsqr1 = zsqr0;

    ///////////////////////////

    //z = z.mult(z);
    //z = z.add(c);

    //zx = z.real();
    //zy = z.imag();
    //cx = c.real();
    //cy = c.imag();

    //temp = z.sum_of_squares();
    ////////////////////////////////

    //tzr = zx*zx - zy*zy + cx;
    //tzi = 2*zx*zy + cy;

    //zx = tzr;
    //zy = tzi;

    //zsqrX = zr*zr;
    //zsqrY = zi*zi;
    //zsqr0 = zsqrX + zsqrY;
    //if ((Math.abs(zsqr0-zsqr1) < zerotol) || (zr > 4) || (zi > 4))  // biomorph method
    //if ((Math.abs(zsqr0-zsqr1) < zerotol) || (zr > 4) || (zi > 4))  // biomorph method

    //temp = zx*zx+zy*zy;

    //if (temp > Bailout)
    //break;

    /*
         if (temp < Bailout)
      System.out.println("Iteration = " + Iteration);
         else
      break;
     */

    m.zsqrX = m.z.real() * m.z.real();
    m.zsqrY = m.z.imag() * m.z.imag();

    //if ((m.zsqrX+m.zsqrY) > m.Bailout ||
    //	(Math.abs(m.zsqr1-m.zsqr0) < m.zerotol))

    temp = Math.sqrt(m.zsqr1 - m.zsqr0);
    //temp = Math.sqrt(temp);

    /*
         if (( temp < m.zerotol) || (temp > m.Bailout))
         {
      m.zsqr1 = m.zsqr0;
      return true;
         }
         else
         {
      m.zsqr1 = m.zsqr0;
      return false;
         } */

    //m.xtot = m.Iteration / (double) m.Maxit;
    //m.ytot = m.Iteration / (double) m.Maxit;
    if (m.Iteration == 0)
    {
      m.xtot = 1;
      m.ytot = 1;
    }
    else
    {
      m.xtot += Math.abs(m.zsqrX+Math.sin(m.zsqrX*10));
      m.ytot += Math.abs(m.zsqrY+Math.sin(m.zsqrY*10));

     /*
      if (m.zsqr0 > m.zsqr1)
        m.xtot += m.xtot/(1 + delta);
      else
        m.ytot += m.ytot/(1 + delta);

      if (m.zsqr0 > m.zsqr1)
        m.xtot += 1/(1 + delta);
      else
        m.ytot += 1/(1 + delta); */
    }

    if ( (delta < m.zerotol) || (delta > m.overflow))
      //if (( delta < m.zerotol) || (delta > 1))
      return true;
    else
      return false;
  }

  public void post_filter()
  {
    //JOptionPane.showMessageDialog( null , "render01 post filter, ..." );

    //m.xtot = m.Iteration / (double) m.Maxit;
    //m.ytot = m.Iteration / (double) m.Maxit;

    /*
    if (m.Iteration <= 2)
    {
      m.xtot = 2;
      m.ytot = 2;
    }
    else
    {
      m.xtot = m.Iteration;
      m.ytot = m.Iteration;
    } */
  }
}
