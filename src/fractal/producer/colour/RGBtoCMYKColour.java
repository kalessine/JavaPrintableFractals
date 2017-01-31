/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.producer.colour;

import fractal.producer.result.PixelValue;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is part of SdmxSax.
 *
 * SdmxSax is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * SdmxSax is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * SdmxSax. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright James Gardner 2014
 */
public class RGBtoCMYKColour implements Colouring {

    public String toString() {
        return "RGB to CMYK";
    }

    public ColourBlend getBlend() {
        return null;
    }

    public void setBlend(ColourBlend c) {
    }
    public static ColorSpace CMYK = null;

    static {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = RGBtoCMYKColour.class.getResourceAsStream("ISOcoated_v2_eci.icc");
        int read;
        try {
            read = in.read();

            while (read != -1) {
                baos.write(read);
                read = in.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(RGBtoCMYKColour.class.getName()).log(Level.SEVERE, null, ex);
        }
        CMYK = new ICC_ColorSpace(ICC_Profile.getInstance(baos.toByteArray()));
    }

    public static float[] rgbToCMYK(float... rgb) {
        float[] toRGB = CMYK.fromRGB(rgb);
        return toRGB;
    }

    public static float[] cmykToRgb(float... cmyk) {
        float[] fromRGB = CMYK.toRGB(cmyk);
        return fromRGB;
    }

    
    public Color getColour(PixelValue pv, ColourMap cmap, Color oldColour, HashMap map) {
        float[] col = new float[3];
        col = oldColour.getColorComponents(col);
        float[] cmyk = rgbToCMYK(col[0], col[1], col[2]);
        col = RGBtoCMYK.toRGB((double)cmyk[0], (double)cmyk[1], (double)cmyk[2], (double)cmyk[3]);
        //.fromRGB(oldColour.getComponents(col));
        return new Color((int)col[0], (int)col[1], (int)col[2]);
    }

    public Colouring clone() {
        return new RGBtoCMYKColour();
    }
}
