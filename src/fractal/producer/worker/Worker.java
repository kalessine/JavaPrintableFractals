/*
 * Worker.java
 *
 * Created on 12 July 2007, 00:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.worker;

import fractal.producer.exception.RenderException;
import fractal.producer.io.RandomAccessFileInputStream;
import fractal.producer.io.RandomAccessFileOutputStream;
import fractal.producer.render.RenderListener;
import fractal.producer.render.chunkrenderer.FractalChunk;
import fractal.producer.result.PixelValue;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Owner
 */
public class Worker implements Runnable,RenderListener {
    
    Thread t = new Thread(this);
    private String directory = "C:\\Render\\";
    boolean running = true;
    FractalChunk last = null;
    FractalChunk current = null;
    /** Creates a new instance of Worker */
    public Worker() {
        t.start();
    }
    boolean working = false;
    
    RandomAccessFile in = null;
    FileChannel channel = null;
    FileLock lock = null;
    
    RandomAccessFile dest = null;
    FileChannel destchannel = null;
    FileLock destlock = null;
    RandomAccessFileOutputStream rafos = null;
    BufferedOutputStream bos = null;
    ObjectOutputStream oos = null;
    
    public void run() {
        while(running) {
            File f = new File(getDirectory());
            File in = new File(f,"In");
            File[] files = in.listFiles();
            if(!working){
                //System.out.println("Sleeping 1000");
                try{Thread.sleep(2000);}catch(InterruptedException ie) {}
                working=false;
            }
            if( files==null||files.length==0 ) working=false;
            if( files!=null){
                int i=(int)(Math.random()*(files.length));
                //System.out.println("I="+i);
                if( i>=0&&files.length>0&&files[i].getName().endsWith(".fcr")&&files[i].canWrite() ) {
                    String name = files[i].getName();
                    String outname = name.substring(0,name.length()-4);
                    outname = outname+".fch";
                    File outfile = new File(new File(getDirectory(),"Out"),outname);
                    if( !outfile.exists()) {
                        working=true;
                        boolean saved =false;
                        try{
                            long start= System.currentTimeMillis();
                            load(files[i]);
                            long load = System.currentTimeMillis();
                            //System.out.println("Process="+(load-start));
                            render();
                            long render = System.currentTimeMillis();
                            //System.out.println("Render="+(render-load));
                            File dir = new File(getDirectory());
                            File out = new File(dir,"Out");
                            if( !out.exists() ) out.mkdirs();
                            File c = new File(out,current.getFileName());
                            if( c.exists() ) c.delete();
                            if( !c.exists() ) {
                                dest = new RandomAccessFile(c,"rws");
                                dest.seek(0l);
                                destchannel = dest.getChannel();
                                destlock = destchannel.lock();
                                rafos = new RandomAccessFileOutputStream(dest);
                                bos = new BufferedOutputStream(rafos);
                                oos = new ObjectOutputStream(bos);
                                oos.writeUnshared(current);
                            }
                            saved=true;
                            current=null;
                            System.gc();
                            //long save = System.currentTimeMillis();
                            //System.out.println("Save="+(save-render));
                        }catch(IOException io) {System.out.println("Worker:IOException");
                        io.printStackTrace();
                        } catch(ClassNotFoundException cnfe) {
                            System.out.println("Worker:ClassNotFoundException");
                        } catch(RenderException re) {System.out.println("Worker:RenderException");
                        }catch(Exception ex) {
                            System.out.println("Worker:Exception:"+ex);
                            ex.printStackTrace();
                        } finally{
                            try{
                                if( lock!=null) lock.release();
                            }catch(IOException io) {
                                io.printStackTrace();
                            }
                            try{
                                if( channel!=null) channel.close();
                            }catch(IOException io) {
                                io.printStackTrace();
                            }
                            try{
                                if( oos!=null)oos.close();
                            }catch(IOException io) {
                                io.printStackTrace();
                            }
                            try{
                                if( bos!=null) bos.close();
                            }catch(IOException io) {
                                io.printStackTrace();
                            }
                            try{
                                if( rafos !=null) rafos.close();
                            }catch(IOException io) {
                                io.printStackTrace();
                            }
                            try{
                                if( destlock!=null) destlock.release();
                            }catch(IOException io) {
                                io.printStackTrace();
                            }
                            try{
                                if( destchannel!=null)destchannel.close();
                            }catch(IOException io) {
                                io.printStackTrace();
                            }
                            try{
                                if( dest!=null) dest.close();
                            }catch(IOException io) {
                                io.printStackTrace();
                            }
                            if(saved)files[i].delete();
                        }
                    }
                }
            }
            //System.out.println("end of loop");
        }
    }
    public void load(File f) throws IOException,ClassNotFoundException {
        in = new RandomAccessFile(f,"rws");
        channel = in.getChannel();
        lock = channel.lock();
        RandomAccessFileInputStream rafis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;
        try{
            rafis = new RandomAccessFileInputStream(in);
            // Buffer Me Up Baby!!!
            bis = new BufferedInputStream(rafis,(int)f.length());
            ois = new ObjectInputStream(bis);
            current = (FractalChunk)ois.readObject();
        }catch(Exception ex){ex.printStackTrace();} finally{
            try{
                if(ois!=null)ois.close();
                if(bis!=null)bis.close();
                if(rafis!=null)rafis.close();
            }catch(Exception ex) {ex.printStackTrace();}
        }
        
    }
    public void render() throws RenderException {
        current.addRenderListener(this);
        current.calculate();
        current.removeRenderListener(this);
    }
    
    public FractalChunk getLastFractalChunk() { return last; }
    public FractalChunk getFractalChunk() { return current; }
    public void stop() { running=false; }
    public boolean isRunning() { return running; }
    public void start() {
        if(running)return;
        t=new Thread(this);
        t.start();
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
    public void renderStarted(){
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.renderStarted();
        }
    }
    public void renderFinished(){
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.renderFinished();
        }
    }
    public void updatePixel(int x, int y, PixelValue pixel){
        Iterator it = listeners.iterator();
        while(it.hasNext()) {
            RenderListener rl = (RenderListener)it.next();
            rl.updatePixel(x,y,pixel);
        }
    }
    public void updateWholeView(BufferedImage bi) {}
    public String getDirectory() {
        return directory;
    }
    
    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
