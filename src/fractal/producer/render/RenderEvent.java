/*
 * RenderEvent.java
 *
 * Created on 9 February 2007, 10:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.render;

/**
 *
 * @author Owner
 */
public class RenderEvent {
    
    /** Creates a new instance of RenderEvent */
    Renderer source = null;
    public RenderEvent(Renderer source) {
        this.source=source;
    }
    public Renderer getSource() { return source; }
}
