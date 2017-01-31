package fractal.producer.colour;
/*
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this program; if not, write to the Free 
 * Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, 
 * MA  02111-1307, USA.
 */
//package no.geosoft.cc.color.ui;

import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;

/**
 * Common color utilities.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public class ColorUtil {

    /**
     * Blend two colors.
     *
     * @param color1 First color to blend.
     * @param color2 Second color to blend.
     * @param ratio Blend ratio. 0.5 will give even blend, 1.0 will return
     * color1, 0.0 will return color2 and so on.
     * @return Blended color.
     */
    public static Color blend(Color color1, Color color2, double ratio) {
        if (ratio > 1.0d || ratio < 0d) {
            throw new IllegalArgumentException("Ratio " + ratio + " greater than 1.0 or less than 0");
        }
        float r = 1f - (float) ratio;
        float rgb1[] = new float[3];
        float rgb2[] = new float[3];
        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);
        float diff[] = new float[3];
        for (int i = 0; i < diff.length; i++) {
            diff[i] = rgb2[i] - rgb1[i];
        }
        Color color = new Color(rgb1[0] + diff[0] * r,
                rgb1[1] + diff[1] * r,
                rgb1[2] + diff[2] * r);

        return color;
    }

    /**
     * Make an even blend between two colors.
     *
     * @param c1 First color to blend.
     * @param c2 Second color to blend.
     * @return Blended color.
     */
    public static Color blend(Color color1, Color color2) {
        return ColorUtil.blend(color1, color2, 0.5);
    }

    /**
     * Make a color darker.
     *
     * @param color Color to make darker.
     * @param fraction Darkness fraction.
     * @return Darker color.
     */
    public static Color darker(Color color, double fraction) {
        int red = (int) Math.round(color.getRed() * (1.0 - fraction));
        int green = (int) Math.round(color.getGreen() * (1.0 - fraction));
        int blue = (int) Math.round(color.getBlue() * (1.0 - fraction));

        if (red < 0) {
            red = 0;
        } else if (red > 255) {
            red = 255;
        }
        if (green < 0) {
            green = 0;
        } else if (green > 255) {
            green = 255;
        }
        if (blue < 0) {
            blue = 0;
        } else if (blue > 255) {
            blue = 255;
        }

        int alpha = color.getAlpha();

        return new Color(red, green, blue, alpha);
    }

    /**
     * Make a color lighter.
     *
     * @param color Color to make lighter.
     * @param fraction Darkness fraction.
     * @return Lighter color.
     */
    public static Color lighter(Color color, double fraction) {
        int red = (int) Math.round(color.getRed() * (1.0 + fraction));
        int green = (int) Math.round(color.getGreen() * (1.0 + fraction));
        int blue = (int) Math.round(color.getBlue() * (1.0 + fraction));

        if (red < 0) {
            red = 0;
        } else if (red > 255) {
            red = 255;
        }
        if (green < 0) {
            green = 0;
        } else if (green > 255) {
            green = 255;
        }
        if (blue < 0) {
            blue = 0;
        } else if (blue > 255) {
            blue = 255;
        }

        int alpha = color.getAlpha();

        return new Color(red, green, blue, alpha);
    }

    /**
     * Return the hex name of a specified color.
     *
     * @param color Color to get hex name of.
     * @return Hex name of color: "rrggbb".
     */
    public static String getHexName(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        String rHex = Integer.toString(r, 16);
        String gHex = Integer.toString(g, 16);
        String bHex = Integer.toString(b, 16);

        return (rHex.length() == 2 ? "" + rHex : "0" + rHex)
                + (gHex.length() == 2 ? "" + gHex : "0" + gHex)
                + (bHex.length() == 2 ? "" + bHex : "0" + bHex);
    }

    /**
     * Return the "distance" between two colors. The rgb entries are taken to be
     * coordinates in a 3D space [0.0-1.0], and this method returnes the
     * distance between the coordinates for the first and second color.
     *
     * @param r1, g1, b1 First color.
     * @param r2, g2, b2 Second color.
     * @return Distance bwetween colors.
     */
    public static double colorDistance(double r1, double g1, double b1,
            double r2, double g2, double b2) {
        double a = r2 - r1;
        double b = g2 - g1;
        double c = b2 - b1;
        return Math.sqrt(a * a + b * b + c * c);
    }

    /**
     * Return the "distance" between two colors.
     *
     * @param color1 First color [r,g,b].
     * @param color2 Second color [r,g,b].
     * @return Distance bwetween colors.
     */
    public static double colorDistance(double[] color1, double[] color2) {
        return ColorUtil.colorDistance(color1[0], color1[1], color1[2],
                color2[0], color2[1], color2[2]);
    }

    /**
     * Return the "distance" between two colors.
     *
     * @param color1 First color.
     * @param color2 Second color.
     * @return Distance between colors.
     */
    public static double colorDistance(Color color1, Color color2) {
        float rgb1[] = new float[3];
        float rgb2[] = new float[3];

        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);

        return ColorUtil.colorDistance(rgb1[0], rgb1[1], rgb1[2],
                rgb2[0], rgb2[1], rgb2[2]);
    }

    /**
     * Check if a color is more dark than light. Useful if an entity of this
     * color is to be labeled: Use white label on a "dark" color and black label
     * on a "light" color.
     *
     * @param r,g,b Color to check.
     * @return True if this is a "dark" color, false otherwise.
     */
    public static boolean isDark(double r, double g, double b) {
        // Measure distance to white and black respectively
        double dWhite = ColorUtil.colorDistance(r, g, b, 1.0, 1.0, 1.0);
        double dBlack = ColorUtil.colorDistance(r, g, b, 0.0, 0.0, 0.0);

        return dBlack < dWhite;
    }

    /**
     * Check if a color is more dark than light. Useful if an entity of this
     * color is to be labeled: Use white label on a "dark" color and black label
     * on a "light" color.
     *
     * @param color Color to check.
     * @return True if this is a "dark" color, false otherwise.
     */
    public static boolean isDark(Color color) {
        float r = color.getRed() / 255.0f;
        float g = color.getGreen() / 255.0f;
        float b = color.getBlue() / 255.0f;

        return isDark(r, g, b);
    }

    public static Color inverse(Color c) {

        float rgb[] = new float[3];
        c.getColorComponents(rgb);
        Color color = new Color(1f - rgb[0],
                1f - rgb[1],
                1f - rgb[2]);

        return color;
    }

    public static Color blendAlpha(Color under, Color over) {
        float rgb1[] = new float[3];
        float rgb2[] = new float[3];

        under.getColorComponents(rgb1);
        over.getColorComponents(rgb2);
        int ualpha = under.getAlpha();
        int oalpha = over.getAlpha();
        float u = ualpha / 255f;
        float o = oalpha / 255f;
        u *= u * o;
        Color color = new Color(rgb1[0] * u + rgb2[0] * o,
                rgb1[1] * u + rgb2[1] * o,
                rgb1[2] * u + rgb2[2] * o);
        return color;
    }

    /**
     * Default screen gamma on Windows is 2.2.
     */
    static final double GAMMA = 2.2;
    static final double GAMMA_INV = 1. / GAMMA;
    /**
     * A lookup table for the conversion from gamma-corrected sRGB values
     * [0..255] to linear RGB values [0..32767].
     */
    static final short[] rgb2lin_red_LUT;

    static {
        // initialize rgb2lin_red_LUT
        rgb2lin_red_LUT = new short[256];
        for (int i = 0; i < 256; i++) {
            // compute linear rgb between 0 and 1
            final double lin = (0.992052 * Math.pow(i / 255., GAMMA) + 0.003974);

            // scale linear rgb to 0..32767
            rgb2lin_red_LUT[i] = (short) (lin * 32767.);
        }
    }
    /**
     * A lookup table for the conversion of linear RGB values [0..255] to
     * gamma-corrected sRGB values [0..255].
     */
    static final byte[] lin2rgb_LUT;

    static {
        // initialize lin2rgb_LUT
        lin2rgb_LUT = new byte[256];
        for (int i = 0; i < 256; i++) {
            lin2rgb_LUT[i] = (byte) (255. * Math.pow(i / 255., GAMMA_INV));
        }
    }
    private static int k1=9591;
    private static int k2=23173;
    private static int k3=-730;

    public static Color deutan(Color c) {
        int in = c.getRGB();
        final int r = (0xff0000 & in) >> 16;
        final int g = (0xff00 & in) >> 8;
        final int b = 0xff & in;

        // get linear rgb values in the range 0..2^15-1
        final int r_lin = rgb2lin_red_LUT[r];
        final int g_lin = rgb2lin_red_LUT[g];
        final int b_lin = rgb2lin_red_LUT[b];

        // simulated red and green are identical
        // scale the matrix values to 0..2^15 for integer computations 
        // of the simulated protan values.
        // divide after the computation by 2^15 to rescale.
        // also divide by 2^15 and multiply by 2^8 to scale the linear rgb to 0..255
        // total division is by 2^15 * 2^15 / 2^8 = 2^22
        // shift the bits by 22 places instead of dividing
        int r_blind = (int) (k1 * r_lin + k2 * g_lin) >> 22;
        int b_blind = (int) (k3 * r_lin - k3 * g_lin + 32768 * b_lin) >> 22;

        if (r_blind < 0) {
            r_blind = 0;
        } else if (r_blind > 255) {
            r_blind = 255;
        }

        if (b_blind < 0) {
            b_blind = 0;
        } else if (b_blind > 255) {
            b_blind = 255;
        }

        // convert reduced linear rgb to gamma corrected rgb
        int red = lin2rgb_LUT[r_blind];
        red = red >= 0 ? red : 256 + red; // from unsigned to signed
        int blue = lin2rgb_LUT[b_blind];
        blue = blue >= 0 ? blue : 256 + blue;   // from unsigned to signed

        final int out = 0xff000000 | red << 16 | red << 8 | blue;
        return new Color(out);
    }
}
