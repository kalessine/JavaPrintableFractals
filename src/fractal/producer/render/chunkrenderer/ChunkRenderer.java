package fractal.producer.render.chunkrenderer;

import fractal.producer.calc.ComplexNumberFactory;
import fractal.producer.colour.ColourMap;
import fractal.producer.PrecisionContext;
import fractal.producer.Coordinates;
import fractal.producer.exception.RenderException;
import fractal.producer.render.RenderListener;
import fractal.producer.render.Renderer;
import fractal.producer.result.PixelValue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class ChunkRenderer implements Renderer {
    
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
    int state = Renderer.NOT_READY;
    PixelValue[][] pixels = null;
    Thread t = null;
    int forceLoops = 0;
    Exception renderException = null;
    
    private int colWidth = 40;
    private int rowHeight = 40;
    
    private boolean updateFractalView = true;
    BufferedImage image = null;
    private boolean[][] loaded = null;
    
    private boolean deleteChunks=true;
    private boolean deleteMaster=true;
    private boolean saveJpeg = false;
    // Chunk Renderer Specific Things
    private String directory = "C:\\Render\\";
    private long render = System.currentTimeMillis();
    /**
     * Creates a new instance of Fractal
     */
    public ChunkRenderer() {
        
    }
    private int columns = width/getColWidth();
    private int rows = height/getRowHeight();
    int leftOverWidth = width%getColWidth();
    int leftOverHeight = height%getRowHeight();
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
                    setRender(System.currentTimeMillis());
                    // Split Pixels Into Chunks
                    
                    File dir = new File(directory);
                    File in = new File(dir,"In");
                    if( !in.exists() ) in.mkdirs();
                    pixels=null;
                    //if( isUpdateFractalView() ) {
                        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                        pixels = new PixelValue[height][width];
                        loaded = new boolean[height][width];
                        for(int i=0;i<loaded.length;i++)
                            for(int j=0;j<loaded[0].length;j++)
                                loaded[i][j]=false;
                        fireUpdateView();
                    //}
                    columns = width/getColWidth();
                    rows = height/getRowHeight();
                    leftOverWidth = width%getColWidth();
                    leftOverHeight = height%getRowHeight();
                    
                    createChunks();
                    System.out.println("Written All Chunks");
                    if( isUpdateFractalView() )
                        waitForChunks();
                    long fin = System.currentTimeMillis();
                    t = null;
                    rendered=true;
                    System.out.println("Total Render Time="+(fin-start));
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
    public void createChunks()throws IOException {
        columns = width/getColWidth();
        rows = height/getRowHeight();
        for(int i=0; i<getColumns(); i++) {
            for(int j=0; j<getRows();j++){
                createChunk(i,j);
            }
        }
    }
    public void createChunk(int i, int j)throws IOException {
        System.out.println("Creating Chunk"+i+","+j);
        File in = new File(directory,"In");
        if( !in.exists()) in.mkdirs();
        int w = getColWidth();
        int h = getRowHeight();
        leftOverWidth = width%getColWidth();
        leftOverHeight = height%getRowHeight();
        System.out.println("LeftOverWidth="+leftOverWidth);
        System.out.println("LeftOverHeight="+leftOverHeight);
        if(i==(getColumns()-1)||j==(getRows())-1)System.out.println("Creating Large Chunk i="+i+" j="+j+" w="+(i==getColumns()-1?w+leftOverWidth:w)+" h="+(j==getRows()-1?h+leftOverHeight:h));
        PixelValue[][] pv = coords.createPixelArray(i*getColWidth(),j*getRowHeight(),i==getColumns()-1?w+leftOverWidth:w,j==getRows()-1?h+leftOverHeight:h,width,height,parameters.getComplexNumberFactory());
        FractalChunk chunk = new FractalChunk(getRender(), pv,i,j,pv[0].length,pv.length);
        chunk.setColourMap(colourMap);
        chunk.setColourings(colourings);
        chunk.setFormulas(formulas);
        chunk.setMaxIteration(maxIteration);
        chunk.setForceLoops(forceLoops);
        chunk.setParameters(parameters);
        chunk.setTransforms(transforms);
        chunk.setCenter(coords.getCenter());
        File ch = new File(in,chunk.getFileName());
        try{
            chunk.save(ch);
        }catch(IOException io) {
            System.out.println("IOException writing chunk file");
            throw io;
        }
        
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
        if(!isUpdateFractalView()) return;
        HashMap map = new HashMap();
        map.put("center",coords.getCenter());
        map.put("maxIteration",getMaxIteration());
        Graphics g = image.getGraphics();
        for(int i=0; i<pixels.length;i++) {
            for(int j=0;j<pixels[i].length;j++){
                pixels[i][j].colour(colourings,getColourMap(),map,background);
                g.setColor(pixels[i][j].getColour());
                g.drawLine(j,i,j,i);
                firePixelRendered(j,i);
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
        //System.out.println("AddRenderListener"+rl);
        listeners.add(rl);
    }
    public void removeRenderListener(RenderListener rl){
        //System.out.println("RemoveRenderListener"+rl);
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
        //if( !isRendered()) throw new RenderException("Must be rendered first!");
        return image;
    }
    public int getWidth() { return width; }
    public void setWidth(int w) {
        if( this.width!=w){
            rendered=false;
        }
        this.width=w;
        this.updateTileSizes();
    }
    public int getHeight() { return height; }
    public void setHeight(int i) {
        if( this.height!=i) {
            rendered=false;
        }
        height=i;
        this.updateTileSizes();
    }
    public void setTransformArray(List list) { transforms=list; }
    public List getTransformArray() { return transforms; }
    
    
    public int getMaxIteration() {
        return maxIteration;
    }
    public void setMaxIteration(int i) {
        maxIteration=i;
    }
    public void setColouringsArray(List list) {
        this.colourings=list;
    }
    public List getColouringsArray() { return colourings; }
    public Color getBackground() { return background; }
    public void setBackground(Color c) { background=c; }
    public PixelValue getPixelValue(int x, int y) {
        return pixels[y][x];
    }
    
    public String getDirectory() {
        return directory;
    }
    
    public void setDirectory(String directory) {
        this.directory = directory;
    }
    public int getForceLoops() { return forceLoops; }
    public void setForceLoops(int i) { this.forceLoops=i; }
    
    public boolean isDeleteChunks() {
        return deleteChunks;
    }
    
    public void setDeleteChunks(boolean deleteChunks) {
        this.deleteChunks = deleteChunks;
    }
    
    public boolean isDeleteMaster() {
        return deleteMaster;
    }
    
    public void setDeleteMaster(boolean deleteMaster) {
        this.deleteMaster = deleteMaster;
    }
    
    public boolean isSaveJpeg() {
        return saveJpeg;
    }
    
    public void setSaveJpeg(boolean saveJpeg) {
        this.saveJpeg = saveJpeg;
    }
    public void waitForChunks() {
        int chunks = 0;
        while( !isFinished() && !stop ) {
            try{Thread.sleep(300);}catch(InterruptedException ie) {}
            loadNewChunks();
        }
    }
    public boolean isFinished() {
        int chunks = getRows()*getColumns();
        for(int i=0;i<getRows();i++) {
            for(int j=0;j<getColumns();j++) {
                if( loaded[j][i] ) chunks--;
            }
        }
        System.out.println("Chunks="+chunks);
        return chunks==0;
    }
    public void fireUpdateView() {
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.updateWholeView(image);
        }
    }
    public int countChunks() {
        File dir = new File(directory);
        File out = new File(dir,"Out");
        if( !out.exists() ) out.mkdirs();
        int chunks = 0;
        for(int i=0; i<getRows(); i++) {
            for(int j=0; j<getColumns();j++){
                File f = new File(out,getRender()+"-"+i+"-"+j+".fch");
                if( f.exists() ) chunks++;
            }
        }
        return chunks;
    }
    public void loadNewChunks() {
        if(!isUpdateFractalView())throw new RuntimeException("Update Fractal View Must Be True!!");
        try{
            File dir = new File(directory);
            File out = new File(dir,"Out");
            if( !out.exists() ) out.mkdirs();
            for(int i=0; i<getRows(); i++) {
                for(int j=0; j<getColumns();j++){
                    File f = new File(out,getRender()+"-"+i+"-"+j+".fch");
                    if( f.exists() && loaded[j][i]==false&&f.canWrite()&&(int)(f.length())> (getColWidth()*getRowHeight())*100 ) {
                        System.out.println("Loading Chunk:"+f.getName());
                        FileInputStream fis = new FileInputStream(f);
                        BufferedInputStream bis = new BufferedInputStream(fis,256000);
                        ObjectInputStream ois = new ObjectInputStream(bis);
                        FractalChunk chunk = (FractalChunk)ois.readObject();
                        ois.close();
                        bis.close();
                        fis.close();
                        PixelValue[][] p = chunk.getPixels();
                        Graphics g = image.getGraphics();
                        int _x = getColWidth()*i;
                        int _y = getRowHeight()*j;
                        for(int x=0;x<p[0].length; x++) {
                            for(int y=0;y<p.length;y++) {
                                g.setColor( p[y][x].getColour() );
                                g.drawLine(_x+x,_y+y,_x+x,_y+y);
                                pixels[_y+y][_x+x]=p[y][x];
                            }
                        }
                        if( deleteChunks) f.delete();
                        loaded[j][i]=true;
                        fireUpdateView();
                    }
                }
            }
        }catch(IOException io) {
            io.printStackTrace();
        }catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    
    public int getColWidth() {
        return colWidth;
    }
    
    public void setColWidth(int colWidth) {
        this.colWidth = colWidth;
        this.updateTileSizes();
    }
    
    public int getRowHeight() {
        return rowHeight;
    }
    
    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
        this.updateTileSizes();
    }
    
    public boolean isUpdateFractalView() {
        return updateFractalView;
    }
    
    public void setUpdateFractalView(boolean updateFractalView) {
        this.updateFractalView = updateFractalView;
    }
    
    public long getRender() {
        return render;
    }
    
    public void setRender(long render) {
        this.render = render;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
    public void updateTileSizes() {
        columns = width/getColWidth();
        rows = height/getRowHeight();
        leftOverWidth = width%getColWidth();
        leftOverHeight = height%getRowHeight();

    }
}