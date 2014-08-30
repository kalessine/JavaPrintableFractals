/////////////////////////
// render01

package emberj.rendering;

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
public class render04
  extends RenderingComponent
{
  private Mandelbrot m;
  private double xtmp, ytmp;
  private double delta;

  public render04()
  {
  }

  public void init(Mandelbrot M)
  {
    m = M;
    System.out.println("init r04");
  }

  public boolean filter()
  {
    delta = Math.abs(m.zsqr1 - m.zsqr0);
    delta *= delta;

    if ( (delta < m.zerotol) || (delta > m.overflow))
      return true;

    if (m.Iteration == 0)
    {
      m.xtot = 1;
      m.ytot = 1;
    }
    else
    {
      xtmp = Math.abs(m.z.real()*m.z.real());
      ytmp = Math.abs(m.z.imag()*m.z.imag());

      m.zsqrX = m.z.real() * m.z.real();
      m.zsqrY = m.z.imag() * m.z.imag();

      int render_option = 0;
      switch (render_option)
      {
        case 1:

          // not too bad
          xtmp = Math.abs(Math.abs(xtmp) - Math.abs(ytmp) - Math.sin(ytmp));
          ytmp = Math.abs(Math.abs(xtmp) - Math.abs(xtmp) - Math.sin(xtmp));
          break;

        case 2:

          // this is OK
          xtmp = Math.abs(xtmp + xtmp - ytmp - Math.sin(ytmp * ytmp));
          ytmp = Math.abs(xtmp + xtmp - xtmp - Math.sin(xtmp * xtmp));
          break;

        case 3:

          // OK 2
          xtmp = Math.abs(ytmp - Math.sin(xtmp - Math.sin(ytmp)));
          ytmp = Math.abs(xtmp - Math.sin(ytmp - Math.sin(xtmp)));
          break;

        case 4:

          // OK 3
          xtmp = Math.abs(xtmp * xtmp - Math.abs(ytmp - Math.cos(xtmp)));
          ytmp = Math.abs(ytmp * ytmp - Math.abs(xtmp - Math.cos(ytmp)));
          break;

        case 5:

          // ok 4
          xtmp = Math.abs(ytmp * ytmp - Math.cos(xtmp));
          ytmp = Math.abs(xtmp * xtmp - Math.cos(ytmp));
          break;

        case 6:

          // OK 5
          xtmp = Math.abs(ytmp - xtmp - Math.cos(xtmp) - Math.cos(xtmp * xtmp));
          ytmp = Math.abs(xtmp - ytmp - Math.cos(ytmp) - Math.cos(ytmp * ytmp));
          break;

        case 7:

          // Maybe
          xtmp = Math.abs(ytmp - xtmp - Math.cos(xtmp - Math.cos(xtmp * xtmp)));
          ytmp = Math.abs(xtmp - ytmp - Math.cos(ytmp - Math.cos(ytmp * ytmp)));
          break;

      }

      if (xtmp < m.dStrands)
        m.xtot = 1 / (1 - (xtmp / m.dStrands));

      if (ytmp < m.dStrands)
        m.ytot = 1 / (1 - (ytmp / m.dStrands));
    }

    return false;
  }

  public void post_filter()
  {
  }
}
