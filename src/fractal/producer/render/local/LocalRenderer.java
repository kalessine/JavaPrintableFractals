package fractal.producer.render.local;

import fractal.producer.calc.ComplexNumberFactory;
import fractal.producer.colour.ColourMap;
import fractal.producer.PrecisionContext;
import fractal.producer.Coordinates;
import fractal.producer.exception.RenderException;
import fractal.producer.orbittrap.OrbitTrap;
import fractal.producer.render.*;
import fractal.producer.result.PixelValue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This Renderer is the default renderer, and will
 * do all rendering locally.
 * @author Owner
 */
public class LocalRenderer implements Renderer {
    
    boolean stop = false;
    boolean rendered = false;
    int width = 1; // Zeroes are bad
    int height = 1;
    private List formulas = null;
    private List transforms = null;
    private List colourings = null;
    private Coordinates coords = null;
    private PrecisionContext parameters = null;
    private ColourMap colourMap = null;
    private Color background = null;
    int maxIteration=255;
    int forceLoops = 0;
    int state = Renderer.NOT_READY;
    PixelValue[][] pixels = null;
    Thread t = null;
    
    Exception renderException = null;
    
    /**
     * Creates a new instance of Fractal
     */
    public LocalRenderer() {
        
    }
    // Call this method to start render
    public synchronized void startRendering()throws RenderException {
        System.out.println("startRender");
        if( coords == null ) throw new RenderException("Coordinates cannot be null");
        if( formulas == null ) throw new RenderException("Formula cannot be null");
        if( formulas.size() < 1 ) throw new RenderException("Must have at least 1 formula");
        if( parameters == null ) throw new RenderException("Parameters cannot be null");
        if( colourMap == null ) throw new RenderException("colourMap cannot be null");
        if( width == 0 || height == 0 ) throw new RenderException("width and height cannot be null");
        if( t!=null ) return;
        rendered=false;
        t=new Thread() {
            public void run() {
                try{
                    fireRenderStarted();
                    state = Renderer.RENDERING;
                    long start = System.currentTimeMillis();
                    ComplexNumberFactory factory = parameters.getComplexNumberFactory();
                    MathContext context = parameters.getMathContext();
                    pixels = coords.createPixelArray(width,height,factory,pixels);
                    HashMap map = new HashMap();
                    map.put("center",coords.getCenter());
                    map.put("maxIteration",getMaxIteration());
                    List orbittraps = new ArrayList(0);
                    for(int i=0; i<colourings.size(); i++) {
                        Object o = colourings.get(i);
                        if( o instanceof OrbitTrap) orbittraps.add(o);
                    }
                    for(int y=0;y<pixels.length&&!stop;y++){
                        for(int x=0;x<pixels[y].length&&!stop;x++){
                            pixels[y][x].transform(transforms,map);
                            pixels[y][x].calculate(formulas,parameters,map,maxIteration,orbittraps,forceLoops);
                            pixels[y][x].colour(colourings,colourMap,map,background);
                            firePixelRendered(x,y);
                        }
                    }
                    long fin = System.currentTimeMillis();
                    t = null;
                    rendered=true;
                    System.out.println("Render="+(fin-start));
                    state = Renderer.RENDERED;
                    if( !stop )
                        fireRenderFinished();
                    stop=false;
                }catch(Exception re) {
                    renderException=re;
                    re.printStackTrace();
                    state=ERROR;
                    t=null;
                    rendered=true;
                    stop=false;
                }
            }
        };
        t.start();
    }
    public int getState() {
        return state;
    }
    public boolean isReady() {
        return this.formulas.size()!=0&&this.formulas!=null&&this.colourMap!=null&&this.parameters!=null&&this.width!=0&&this.height!=0;
    }
    // Returns True when this renderer is working
    public boolean isRendering() { return t!=null; }
    // If You Wish to cancel the render
    public void interruptRender() {
        if( !isRendering())return;
        stop=true;
        while(!isRendered()) {
            try{
                Thread.sleep(40);
            }catch(InterruptedException tie) {}
        }
        System.out.println("InterruptRender=isRendering="+isRendering());
    }
    // Returns True When All Steps for All pixels are finished
    public boolean isRendered() { return rendered; }
    
    public void init() {
        if( isRendering() ) return;
        this.state = Renderer.READY_TO_RENDER;
        rendered=false;
    }
    
    public void colourAll() {
        HashMap map = new HashMap();
        map.put("center",coords.getCenter());
        map.put("maxIteration",getMaxIteration());
        for(int i=0; i<pixels.length;i++) {
            for(int j=0;j<pixels[i].length;j++){
                pixels[i][j].colour(colourings,getColourMap(),map,background);
//                firePixelRendered(j,i);
            }
        }
        fireRenderFinished();
    }
    
    public ColourMap getColourMap() {
        return colourMap;
    }
    
    public void setColourMap(ColourMap colourMap) {
        this.colourMap = colourMap;
    }
    List listeners = new ArrayList(0);
    public void addRenderListener(RenderListener rl){
        listeners.add(rl);
    }
    public void removeRenderListener(RenderListener rl){
        listeners.remove(rl);
    }
    public void fireRenderStarted() {
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.renderStarted();
        }
    }
    public void fireRenderFinished() {
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.renderFinished();
        }
    }
    public void firePixelRendered(int x, int y) {
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.updatePixel(x,y,pixels[y][x]);
        }
    }
    public List getFormulaArray() {
        return formulas;
    }
    public void setFormulaArray(List f) {
        this.formulas = f;
    }
    public PrecisionContext getPrecisionContext() {
        return parameters;
    }
    public void setPrecisionContext(PrecisionContext params) {
        this.parameters = params;
    }
    public void setCoordinates(Coordinates c){
        coords=c;
    }
    public Coordinates getCoordinates(){
        return coords;
    }
    public BufferedImage getImage() throws RenderException{
        if( !isRendered()) throw new RenderException("Must be rendered first!");
        /*if( renderException != null ) {
            Exception e = renderException;
            renderException=null;
            throw new RenderException(e.getMessage());
        }*/
        BufferedImage bi=null;
        try{
            bi = new BufferedImage(pixels[0].length,pixels.length,BufferedImage.TYPE_INT_RGB);
        }catch(java.lang.OutOfMemoryError oome) {
            throw new RenderException("Out of Memory - Sorry!!");
        }
        Graphics g = bi.getGraphics();
        for(int y=0; y<pixels.length;y++) {
            for(int x=0;x<pixels[y].length;x++){
                g.setColor(pixels[y][x].getColour());
                g.drawLine(x,y,x,y);
            }
        }
        return bi;
    }
    public int getWidth() { return width; }
    public void setWidth(int w) {
        if( this.width!=w){
            rendered=false;
        }
        this.width=w;
    }
    public int getHeight() { return height; }
    public void setHeight(int i) {
        if( this.height!=i) {
            rendered=false;
        }
        height=i;
    }
    public void setTransformArray(List list) { transforms=list; }
    public List getTransformArray() { return transforms; }
    
    public int getMaxIteration() {
        return maxIteration;
    }
    public void setMaxIteration(int i) { 
        maxIteration=i; 
    }
    
    public int getForceLoops() {
        return forceLoops;
    }
    public void setForceLoops(int i) { 
        forceLoops=i; 
    }
    public void setColouringsArray(List list) {
        this.colourings=list;
    }
    public List getColouringsArray() { return colourings; }
    public Color getBackground() { return background; }
    public void setBackground(Color c) { background=c; }
    public PixelValue getPixelValue(int x, int y) { return pixels[y][x]; }
    
}