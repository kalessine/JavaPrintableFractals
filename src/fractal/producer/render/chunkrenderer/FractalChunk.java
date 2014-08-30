/*
 * FractalChunk.java
 *
 * Created on 5 July 2007, 19:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.render.chunkrenderer;

import fractal.producer.PrecisionContext;
import fractal.producer.calc.ComplexNumber;
import fractal.producer.colour.ColourMap;
import fractal.producer.exception.RenderException;
import fractal.producer.formula.Formula;
import fractal.producer.io.RandomAccessFileOutputStream;
import fractal.producer.orbittrap.OrbitTrap;
import fractal.producer.render.RenderListener;
import fractal.producer.result.PixelValue;
import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Owner
 */
public class FractalChunk implements Serializable {
    
    private List transforms = null;
    private List formulas = null;
    private PrecisionContext parameters = null;
    private int maxIteration = 255;
    private int forceLoops = 0;
    private List orbitTraps = null;
    private List colourings = null;
    private ColourMap colourMap = null;
    private Color background = Color.black;
    private HashMap map = null;
    private PixelValue[][] pixels = null;
    String fileName = "";
    long render = 0l;
    private int x = 0;
    private int y = 0;
    private int w = 0;
    private int h = 0;
    private ComplexNumber center = null;
    boolean rendered = false;
    
    /** Creates a new instance of FractalChunk */
    public FractalChunk(long render,PixelValue[][] pix,int x, int y, int w, int h) {
        this.setPixels(pix);
        this.render=render;
        this.x=x;
        this.y=y;
        this.setW(w);
        this.setH(h);
    }
    
    
    private boolean stop = false;
    private boolean error = false;
    public void calculate() {
        fireRenderStarted();
        map = new HashMap();
        map.put(Formula.CENTER,center);
        map.put(Formula.MAX_ITERATION,getMaxIteration());
        List orbittraps = new ArrayList(0);
        for(int i=0; i<colourings.size(); i++) {
            Object o = colourings.get(i);
            if( o instanceof OrbitTrap) orbittraps.add(o);
        }
        for(int _y=0;_y<getPixels().length&&!isStop();_y++){
            for(int _x=0;_x<getPixels()[_y].length&&!isStop();_x++){
                try{
                    pixels[_y][_x].transform(transforms,map);
                    pixels[_y][_x].calculate(formulas,parameters,map,maxIteration,orbittraps,forceLoops);
                    pixels[_y][_x].colour(colourings,colourMap,map,background);
                }catch(RenderException re) {
                    re.printStackTrace();
                    this.error=true;
                    return;
                }
                firePixelRendered(_x,_y);
            }
        }
        rendered=true;
        fireRenderFinished();
    }
    public void save(File f) throws IOException {
        if( f.exists()) f.delete();
        if(f.exists()) return;
        RandomAccessFile raf = null;
        FileChannel channel = null;
        FileLock lock = null;
        RandomAccessFileOutputStream rafos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try{
            raf = new RandomAccessFile(f,"rws");
            channel = raf.getChannel();
            lock = channel.lock();
            rafos = new RandomAccessFileOutputStream(raf);
            // Gotta Love Those Buffers!!!
            bos = new BufferedOutputStream(rafos,4*1024*1024);
            oos = new ObjectOutputStream(bos);
            oos.writeUnshared(this);
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if(oos!=null)oos.close();
            if(bos!=null)bos.close();
            if(rafos!=null)rafos.close();
            if( lock!=null)lock.release();
            if( channel!=null)channel.close();
            if( raf!=null)raf.close();
        }        
    }
    public List getTransforms() {
        return transforms;
    }
    
    public void setTransforms(List transforms) {
        this.transforms = transforms;
    }
    
    public List getFormulas() {
        return formulas;
    }
    
    public void setFormulas(List formulas) {
        this.formulas = formulas;
    }
    
    public PrecisionContext getParameters() {
        return parameters;
    }
    
    public void setParameters(PrecisionContext parameters) {
        this.parameters = parameters;
    }
    
    public int getMaxIteration() {
        return maxIteration;
    }
    
    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }
    
    public List getColourings() {
        return colourings;
    }
    
    public void setColourings(List colourings) {
        this.colourings = colourings;
    }
    
    public ColourMap getColourMap() {
        return colourMap;
    }
    
    public void setColourMap(ColourMap colourMap) {
        this.colourMap = colourMap;
    }
    
    public Color getBackground() {
        return background;
    }
    
    public void setBackground(Color background) {
        this.background = background;
    }
    
    public HashMap getMap() {
        return map;
    }
    
    public void setMap(HashMap map) {
        this.map = map;
    }
    
    public PixelValue[][] getPixels() {
        return pixels;
    }
    
    public void setPixels(PixelValue[][] pixels) {
        this.pixels = pixels;
    }
    
    public boolean isStop() {
        return stop;
    }
    
    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    public String getFileName() {
        if( !rendered )
            return getFileNameUnprocessed();
        else return getFileNameProcessed();
    }
    public String getFileNameUnprocessed() {
        return render+"-"+x+"-"+y+".fcr";
    }
    public String getFileNameProcessed() {
        return render+"-"+x+"-"+y+".fch";
    }
    
    public int getW() {
        return w;
    }
    
    public void setW(int w) {
        this.w = w;
    }
    
    public int getH() {
        return h;
    }
    
    public void setH(int h) {
        this.h = h;
    }
    
    public ComplexNumber getCenter() {
        return center;
    }
    
    public void setCenter(ComplexNumber center) {
        this.center = center;
    }
    
    public int getForceLoops() {
        return forceLoops;
    }
    
    public void setForceLoops(int forceLoops) {
        this.forceLoops = forceLoops;
    }
    transient ArrayList listeners = new ArrayList(0);
    public void addRenderListener(RenderListener rl){
        if(listeners==null)listeners=new ArrayList(0);
        listeners.add(rl);
    }
    public void removeRenderListener(RenderListener rl){
        if(listeners==null)listeners=new ArrayList(0);
        listeners.remove(rl);
    }
    public void fireRenderStarted() {
        if(listeners==null)listeners=new ArrayList(0);
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.renderStarted();
        }
    }
    public void fireRenderFinished() {
        if(listeners==null)listeners=new ArrayList(0);
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.renderFinished();
        }
    }
    public void firePixelRendered(int x, int y) {
        if(listeners==null)listeners=new ArrayList(0);
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.updatePixel(x,y,pixels[y][x]);
        }
    }
    
}