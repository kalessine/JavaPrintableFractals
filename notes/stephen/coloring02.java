/////////////////////////
// coloring02

import java.awt.*;
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

public class coloring02 extends ColoringComponent
{
  private Mandelbrot m;

  private float hsbStart[] = new float[3];
  private float hsbStop[] = new float[3];

  private float hueStart;
  private float satStart;
  private float brtStart;

  private float hueColor;
  private float satColor;
  private float brtColor;

  private int rgbValue;

  private Color colorStart;
  private Color colorStop;

  private float deltaMax;
  private float deltaMin;

  public coloring02()
  {
  }

  public void init(Mandelbrot M)
  {
    m = M;
    colorStart = m.ps.getBeanData().getColorstart();

    hsbStart = colorStart.getRGBColorComponents(null);

    hueStart = hsbStart[0];
    satStart = hsbStart[1];
    brtStart = hsbStart[2];

    //System.out.println(hsbStart[0] + ", " + hsbStart[1] + ", " + hsbStart[2]);
  }

  public void coloring()
  {
    hueColor = (float) Math.abs(hueStart + (m.ytot + m.xtot) * m.dFactor3);
    satColor = (float) Math.abs(hueColor + satStart * m.dFactor3);
    brtColor = (float) Math.abs(hueColor + brtStart * m.dFactor3);

    //System.out.println("floor = " + Math.floor(hueColor));
    //System.out.println("ceil = " + Math.ceil(hueColor));

    if (satColor > 1)
    {
      if ( (Math.floor(satColor) % 2) == 0)
      {
        satColor = 1 - ((float) (Math.ceil(satColor) - satColor));
      }
      else
      {
        satColor = (float) (Math.ceil(satColor) - satColor);
      }
    }

    if (brtColor > 1)
    {
      if ( (Math.floor(brtColor) % 2) == 0)
      {
        brtColor = 1 - ((float) (Math.ceil(brtColor) - brtColor));
      }
      else
      {
        brtColor = (float) (Math.ceil(brtColor) - brtColor);
      }
    }

    rgbValue = Color.HSBtoRGB(hueStart + hueColor, satColor, brtColor);
    //rgbValue = Color.HSBtoRGB(hueStart, hueColor, brtStart);

    m.pixels[m.index] = rgbValue;
    m.index+=1;
  }
}
