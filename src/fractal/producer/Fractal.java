/*
 * Fractal.java
 *
 * Created on 9 February 2007, 11:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer;

import fractal.producer.calc.M;
import fractal.producer.colour.ColourMap;
import fractal.producer.colour.Colouring;
import fractal.producer.colour.RGBtoCMYKColour;
import fractal.producer.colour.builtin.IterationsColouring;
import fractal.producer.exception.FractalSizeException;
import fractal.producer.exception.RenderException;
import fractal.producer.formula.builtin.Mandelbrot;
import fractal.producer.render.local.LocalRenderer;
import fractal.producer.render.Renderer;
import fractal.producer.transform.Transform;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Owner
 */
public class Fractal implements Serializable {
    
    public static final int ASPECT_SQUARE = 0;
    public static final int ASPECT_A_SERIES_LANDSCAPE = 1;
    public static final int ASPECT_A_SERIES_PORTRAIT = 2;
    
    public static final int LOCK_WIDTH = 0;
    public static final int LOCK_HEIGHT = 1;
    
    private int aspect = ASPECT_SQUARE;
    private int width = 400;
    private int height = 400;
    private int lock = LOCK_WIDTH;
    
    //private Formula formula = new Mandelbrot();
    ArrayList formulas = new ArrayList(0);
    private List transforms = new ArrayList(0);
    private List colourings = new ArrayList(0);
    private Coordinates coords = null;
    private PrecisionContext precisionContext = new PrecisionContext();
    private ColourMap colourMap = new ColourMap();
    private transient Renderer renderer = new LocalRenderer();
    Color background = Color.BLACK;
    /**
     * Creates a new instance of Fractal
     */
    public Fractal() {
        Mandelbrot m = new Mandelbrot();
        formulas.add(m);
        coords = m.getDefaultCoordinates();
        getColourings().add(new IterationsColouring());
        renderer.setCoordinates(coords);
        renderer.setMaxIteration(255);
        renderer.setForceLoops(forceLoops);
        renderer.setWidth(width);
        renderer.setHeight(height);
        renderer.setPrecisionContext(precisionContext);
        renderer.setFormulaArray(formulas);
        renderer.setColourMap(colourMap);
        renderer.setColouringsArray(getColourings());
        getColourings().add(new RGBtoCMYKColour());
    }
    
    public ColourMap getColourMap() {
        return colourMap;
    }
    
    public void setColourMap(ColourMap colourMap) {
        this.colourMap = colourMap;
    }
    
    public List getFormulaArray() {
        return formulas;
    }
    public void setFormulaArray(java.util.ArrayList list) {
        this.formulas=list;
    }
    public PrecisionContext getParameters() {
        return precisionContext;
    }
    public void setParameters(PrecisionContext params) {
        this.precisionContext = params;
    }
    public void setCoordinates(Coordinates c){
        coords=c;
        firePhysicalChanged(this);
    }
    public Coordinates getCoordinates(){
        return coords;
    }
    public BufferedImage getImage() throws RenderException {
        return getRenderer().getImage();
    }
    
    public Renderer getRenderer() {
        return renderer;
    }
    
    public void setRenderer(Renderer renderer) {
        System.out.println("SetRenderer");
        if(listeners == null ) listeners = new ArrayList(0);
        this.renderer = renderer;
        Iterator it = listeners.iterator();
        while(it.hasNext()) { 
           FractalListener l = (FractalListener)it.next();
           l.rendererChanged(this.renderer);
        }
    }
    public void startRender() throws RenderException {
        renderer.setWidth(width);
        renderer.setHeight(height);
        renderer.setCoordinates(coords);
        renderer.setPrecisionContext(precisionContext);
        renderer.setFormulaArray(formulas);
        renderer.setTransformArray(getTransforms());
        renderer.setColourMap(colourMap);
        renderer.setColouringsArray(getColourings());
        renderer.setBackground(background);
        renderer.setForceLoops(forceLoops);
        renderer.startRendering();
    }
    public boolean isRendering() { return renderer.isRendering(); }
    public void interruptRender() {
        renderer.interruptRender();
    }
    
    transient List listeners = new ArrayList(0);
    public void addFractalListener(FractalListener fl) {
        if( listeners==null) listeners = new ArrayList(0);
        listeners.add(fl);
    }
    public void removeFractalListener(FractalListener fl) {
        if( listeners==null) listeners = new ArrayList(0);
        listeners.remove(fl);
    }
    public void firePhysicalChanged(Object source) {
        if( listeners==null) listeners = new ArrayList(0);
        FractalEvent fe = new FractalEvent(this,source);
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            FractalListener fl = (FractalListener)it.next();
            fl.physicalValueChanged(fe);
        }
    }
    public void fireTransformChanged(Object source) {
        FractalEvent fe = new FractalEvent(this,source);
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            FractalListener fl = (FractalListener)it.next();
            fl.transformValueChanged(fe);
        }
    }
    public void fireFormulaChanged(Object source) {
        FractalEvent fe = new FractalEvent(this,source);
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            FractalListener fl = (FractalListener)it.next();
            fl.formulaValueChanged(fe);
        }
    }
    public void fireColourChanged(Object source) {
        renderer.colourAll();
        FractalEvent fe = new FractalEvent(this,source);
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            FractalListener fl = (FractalListener)it.next();
            fl.physicalValueChanged(fe);
        }
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) throws FractalSizeException {
        if( lock == LOCK_WIDTH) throw new FractalSizeException("Width is locked");
        this.width = width;
        renderer.setWidth(width);
        updateAspect();
        this.firePhysicalChanged(this);
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int h)throws FractalSizeException {
        if( lock == LOCK_HEIGHT) throw new FractalSizeException("Height is locked");
        this.height = h;
        renderer.setHeight(h);
        updateAspect();
        this.firePhysicalChanged(this);
    }
    public void setAspect(int aspect) {
        this.aspect = aspect;
        updateAspect();
        firePhysicalChanged(this);
    }
    private void updateAspect() {
        switch(aspect) {
            case ASPECT_SQUARE:
                if( lock != LOCK_WIDTH ){
                    Number w = M.subtract(coords.getRight(),coords.getLeft(),precisionContext.getMathContext());
                    coords.setBottom(M.subtract(coords.getTop(),w,precisionContext.getMathContext()));
                    height=getWidth();
                } else {
                    Number h = M.subtract(coords.getTop(),coords.getBottom(),precisionContext.getMathContext());
                    coords.setRight(M.add(coords.getLeft(),h,precisionContext.getMathContext()));
                    width=getHeight();
                }
                break;
            case ASPECT_A_SERIES_PORTRAIT:
                if( lock != LOCK_WIDTH ){
                    Number w = M.subtract(coords.getRight(),coords.getLeft(),precisionContext.getMathContext());
                    Number nw = M.multiply(w,1.41d,precisionContext.getMathContext());
                    coords.setBottom(M.subtract(coords.getTop(),nw,precisionContext.getMathContext()));
                    height=((int)(getWidth()*1.41d));
                } else{
                    Number h = M.subtract(coords.getTop(),coords.getBottom(),precisionContext.getMathContext());
                    h = M.divide(h,1.41d,precisionContext.getMathContext());
                    coords.setRight(M.add(coords.getLeft(),h,precisionContext.getMathContext()));
                    width=((int)(getHeight()/1.41d));
                }
                break;
            case ASPECT_A_SERIES_LANDSCAPE:
                if( lock != LOCK_WIDTH){
                    Number w = M.subtract(coords.getRight(),coords.getLeft(),precisionContext.getMathContext());
                    Number nw = M.divide(w,1.41d,precisionContext.getMathContext());
                    coords.setBottom(M.subtract(coords.getTop(),nw,precisionContext.getMathContext()));
                    height=((int)(getWidth()/1.41d));
                } else{
                    Number h = M.subtract(coords.getTop(),coords.getBottom(),precisionContext.getMathContext());
                    h = M.multiply(h,1.41d,precisionContext.getMathContext());
                    coords.setRight(M.add(coords.getLeft(),h,precisionContext.getMathContext()));
                    width=((int)(getHeight()*1.41d));
                }
                break;
        }
    }
    public int getAspect() { return aspect; }
    public int getLock() { return lock; }
    public void setLock(int i) { lock=i; firePhysicalChanged(this); }
    public PrecisionContext getPrecisionContext() {
        return this.precisionContext;
    }
    public void setPrecisionContext(PrecisionContext pc) {
        this.precisionContext=pc;
        firePhysicalChanged(this);
    }
    public int getTransformsSize() { return getTransforms().size(); }
    public void addTransform(Transform t) { getTransforms().add(t); }
    public void removeTransform(Transform t) { getTransforms().remove(t); }
    public Transform getTransform(int id) { return (Transform)getTransforms().get(id); }
    
    public int getColouringsSize() { return getColourings().size(); }
    public void addColouring(Colouring t) { getColourings().add(t); }
    public void removeColouring(Colouring t) { getColourings().remove(t); }
    public Colouring getColouring(int id) { return (Colouring)getColourings().get(id); }
    public Color getBackground() { return background; }
    public void setBackground(Color c) { background=c; }
    
    public int getMaxIteration() {
        return renderer.getMaxIteration();
    }
    
    public void setMaxIteration(int maxIteration) {
        System.out.println("Set Max Iteration"+maxIteration);
        Thread.dumpStack();
        renderer.setMaxIteration(maxIteration);
    }
    int forceLoops = 0;
    public int getForceLoops()       { return forceLoops;  }
    public void setForceLoops(int i) { this.forceLoops=i;  }

    public List getTransforms() {
        return transforms;
    }

    public List getColourings() {
        return colourings;
    }
}