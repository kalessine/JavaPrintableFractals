/*
 * Renderer.java
 *
 * Created on 9 February 2007, 10:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.render;

import fractal.producer.colour.ColourMap;
import fractal.producer.PrecisionContext;
import fractal.producer.Coordinates;
import fractal.producer.exception.RenderException;
import fractal.producer.result.PixelValue;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The Idea here is to
 * @author Owner
 */
public interface Renderer {
    
    /* The Renderer is Not Ready to render a fractal
     */
    public static final int NOT_READY = 0;
    public static final int READY_TO_RENDER = 1;
    public static final int RENDERING = 2;
    public static final int RENDERED = 3;
    public static final int ERROR = 99;    
    
    public void addRenderListener(RenderListener rl);
    public void removeRenderListener(RenderListener rl);
    
    public int getState();
    
    // Initialise with width and height
    public void init();
    public boolean isReady();
    
    public void startRendering() throws RenderException;
    // Returns True when this renderer is working
    public boolean isRendering();    
    // Returns True When All Steps for All pixels are finished
    public boolean isRendered();
    public void setCoordinates(Coordinates c);
    public Coordinates getCoordinates();
    public void setTransformArray(List list);
    public List getTransformArray();
    
    public void setFormulaArray(List list);
    public List getFormulaArray();
    
    public void setColouringsArray(List list);
    public List getColouringsArray();

    public void setPrecisionContext(PrecisionContext p);
    public PrecisionContext getPrecisionContext();
// For Now We Have a simple colourmap colouring algorithm.
    public ColourMap getColourMap();
    public void setColourMap(ColourMap cMap);
    // If You Wish to cancel the render
    public void interruptRender();
    public BufferedImage getImage() throws RenderException;
    public int getWidth();
    public void setWidth(int i);
    public int getHeight();
    public void setHeight(int i);
    public void colourAll();
    public int getMaxIteration();
    public void setMaxIteration(int id);
    public Color getBackground();
    public void setBackground(Color c);
    public PixelValue getPixelValue(int x, int y);
    public int getForceLoops();
    public void setForceLoops(int i);
}
