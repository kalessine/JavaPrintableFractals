/*
 * FractalEvent.java
 *
 * Created on 22 February 2007, 21:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer;

/**
 *
 * @author Owner
 */
public class FractalEvent {
    Fractal fractalsource = null;
    Object source = null;
    /** Creates a new instance of FractalEvent */
    public FractalEvent(Fractal fsource,Object sourc) {
        this.fractalsource=fsource;
        this.source=source;
    }
    public Fractal getFractalSource() { return fractalsource; }
    public Object getSource() { return source; }
    
}
