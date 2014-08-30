/*
 * FractalListener.java
 *
 * Created on 22 February 2007, 21:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer;

import fractal.producer.render.Renderer;

/**
 *
 * @author Owner
 */
public interface FractalListener {
    public void physicalValueChanged(FractalEvent fe);
    public void transformValueChanged(FractalEvent fe);
    public void formulaValueChanged(FractalEvent fe);
    public void colourValueChanged(FractalEvent fe);
    public void rendererChanged(Renderer ren);
}
