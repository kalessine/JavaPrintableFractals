/*
 * CreatePdfMaster.java
 *
 * Created on 16 July 2007, 22:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.render.chunkrenderer;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import fractal.producer.Fractal;
import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;

/**
 *
 * @author Owner
 */
public class CreatePdfMaster implements Externalizable {
    DecimalFormat format = new DecimalFormat("0.00");
    private int renderNo = 0;
    private String size = "A5";
    private Rectangle pagesize = PageSize.A5;
    private int dpi = 72;
    private int tileWidth = 72;
    private int tileHeight = 72;
    
    private Fractal fractal = null;
    
    /** Creates a new instance of CreatePdfMaster */
    public CreatePdfMaster() {
    }
    public static final double MILLIMETERS_PER_POINT = 72d/25.40d;
    public double convertPointsToMillimeters(double points) {
        return points/MILLIMETERS_PER_POINT;
    }
    public double convertMillimetersToPoints(double mm) {
        return mm*MILLIMETERS_PER_POINT;
    }
    public String getPageWidthMM() {
        return format.format(convertPointsToMillimeters(getPagesize().getWidth()));
    }
    public String getPageHeightMM() {
        return format.format(convertPointsToMillimeters(getPagesize().getHeight()));
    }
    public int getPixelWidth() {
        if(fractal==null)return 0;
        Rectangle ps = getPagesize();
        double w = ps.getWidth();
        int pw = (int)(w*getDpi()/72d);
        return pw;
    }
    public int getPixelHeight() {
        if(fractal==null)return 0;
        Rectangle ps = getPagesize();
        double h = ps.getHeight();
        int ph = (int)(h*getDpi()/72d);
        return ph;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public Rectangle getPagesize() {
        Rectangle ps = pagesize;
        if( fractal.getAspect()==Fractal.ASPECT_A_SERIES_LANDSCAPE){
            ps = new Rectangle(Math.max(ps.getWidth(),ps.getHeight()),Math.min(ps.getWidth(),ps.getHeight()));
        }
        if( fractal.getAspect()==Fractal.ASPECT_A_SERIES_PORTRAIT){
            ps = new Rectangle(Math.min(ps.getWidth(),ps.getHeight()),Math.max(ps.getWidth(),ps.getHeight()));
        }
        if( fractal.getAspect()==Fractal.ASPECT_SQUARE){
            ps = new Rectangle(Math.min(ps.getWidth(),ps.getHeight()),Math.min(ps.getWidth(),ps.getHeight()));
        }        
        return ps;
    }
    
    public void setPagesize(Rectangle pagesize) {
        this.pagesize = pagesize;
    }
    
    public int getDpi() {
        return dpi;
    }
    
    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
    
    public int getTileWidth() {
        return tileWidth;
    }
    
    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }
    
    public int getTileHeight() {
        return tileHeight;
    }
    
    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
    
    public Fractal getFractal() {
        return fractal;
    }
    
    public void setFractal(Fractal fractal) {
        this.fractal = fractal;
    }
    public void createChunks(ChunkRenderer cr)throws IOException {
        int oldh = cr.getHeight();
        int oldw = cr.getWidth();
        int oldtw = cr.getColWidth();
        int oldth = cr.getRowHeight();
        update(cr);
        File render = new File(cr.getDirectory());
        File in = new File(render,"In");
        File master = new File(render,this.renderNo+".mas");
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(master);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
        }catch(IOException io) { throw io; } finally {
            if(oos!=null)oos.close();
            if(bos!=null)bos.close();
            if(fos!=null)fos.close();
        }
        try{
            cr.createChunks();
        }catch(IOException io) {
            io.printStackTrace();
        } finally{
            cr.setUpdateFractalView(true);
            cr.setWidth(oldw);
            cr.setHeight(oldh);
            cr.setColWidth(oldtw);
            cr.setRowHeight(oldth);
        }
    }
    
    public int getRenderNo() {
        return renderNo;
    }
    
    public void setRenderNo(int renderNo) {
        this.renderNo = renderNo;
    }
    public void readExternal(ObjectInput in)throws IOException, ClassNotFoundException{
        renderNo = in.readInt();
        size = in.readUTF();
        pagesize = new Rectangle(in.readFloat(),in.readFloat());
        dpi = in.readInt();
        tileWidth = in.readInt();
        tileHeight = in.readInt();
        fractal = (Fractal)in.readObject();
    }
    public void writeExternal(ObjectOutput out)throws IOException{
        out.writeInt(renderNo);
        out.writeUTF(size);
        out.writeFloat(pagesize.getWidth());
        out.writeFloat(pagesize.getHeight());
        out.writeInt(dpi);
        out.writeInt(tileWidth);
        out.writeInt(tileHeight);
        out.writeObject(fractal);
    }
    public void update(ChunkRenderer cr) {
        cr.setRender(renderNo);
        cr.setWidth(getPixelWidth());
        cr.setHeight(getPixelHeight());
        cr.setRowHeight(tileHeight);
        cr.setColWidth(tileWidth);
        cr.setCoordinates(fractal.getCoordinates());
        cr.setPrecisionContext(fractal.getPrecisionContext());
        cr.setFormulaArray(fractal.getFormulaArray());
        cr.setTransformArray(fractal.getTransforms());
        cr.setColourMap(fractal.getColourMap());
        cr.setColouringsArray(fractal.getColourings());
        cr.setBackground(Color.black);
        cr.setForceLoops(fractal.getForceLoops());
        cr.setUpdateFractalView(false);
    }/*
    public void restore(ChunkRenderer cr) {
       cr.setWidth(fractal.getWidth());
       cr.setHeight(fractal.getHeight());
       cr.setRowHeight(40);
       cr.setColWidth(40);
       cr.setDeleteChunks(true);
       cr.setUpdateFractalView(true);
    }*/
}
