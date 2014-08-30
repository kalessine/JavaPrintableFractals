/////////////////////////
// render01

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
public class render05 extends RenderingComponent
{
  private Mandelbrot m;
  private double delta;

  public render05()
  {
  }

  public void init(Mandelbrot M)
  {
    m = M;
  }

  public boolean filter()
  {
    delta = Math.abs(m.zsqr1 - m.zsqr0);
    delta *= delta;

    if (( delta < m.zerotol) || (delta > m.overflow))
      return true;

    if (m.Iteration == 0)
    {
      m.xtot = 1;
      m.ytot = 1;
    }
    else
    {
      m.xtot += 1/(1 + delta);
      m.ytot += 1/(1 + delta);
    }

    return false;
  }

  public void post_filter()
  {
    //if (m.dMagnification > 10)
      //System.out.print("d="+ (int) delta);
    //m.xtot = delta;
    //m.ytot = delta;
  }
}
