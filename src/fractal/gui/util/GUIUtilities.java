/*
 * Created on 21/02/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fractal.gui.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JComponent;

/**
 * @author Owner
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GUIUtilities {
    public static void centerChildFrameOnParent(java.awt.Window parent, java.awt.Window child) {
        java.awt.Dimension frameSize = child.getSize();
        java.awt.Dimension screenSize = parent.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        child.setLocation( (screenSize.width - frameSize.width) / 2+parent.getLocation().x, (screenSize.height - frameSize.height) / 2+parent.getLocation().y);
    }
    public static void centerFrameOnScreen(java.awt.Window frame) {
        java.awt.Dimension frameSize = frame.getSize();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation( (screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }
    public static void centerWindowOnWindow(Window parent,Window child){
        Dimension frameSize = parent.getSize();
        Point loc = parent.getLocation();
        Point center = new Point(loc.x+frameSize.width/2,loc.y+frameSize.height/2);
        // Move Left by half frame's width, move up by half frames height
        Point center2 = new Point(center.x-child.getWidth(),center.x-child.getHeight());
        child.setLocation(center2);
    }
    public static final void saveWindowProperties(Properties p, JComponent c,String name) {
        p.put(name+".x",Integer.toString(c.getX()));
        p.put(name+".y",Integer.toString(c.getY()));
        p.put(name+".w",Integer.toString(c.getWidth()));
        p.put(name+".h",Integer.toString(c.getHeight()));
        p.put(name+".visible",Boolean.toString(c.isVisible()));
    }
    public static final void saveWindowProperties(Properties p, Window w,String name) {
        p.put(name+".x",Integer.toString(w.getX()));
        p.put(name+".y",Integer.toString(w.getY()));
        p.put(name+".w",Integer.toString(w.getWidth()));
        p.put(name+".h",Integer.toString(w.getHeight()));
        p.put(name+".visible",Boolean.toString(w.isVisible()));
    }
    public static final void loadWindowProperties(Properties p, JComponent c,String name) {
        c.setBounds(
                Integer.parseInt((String)p.get(name+".x")),
                Integer.parseInt((String)p.get(name+".y")),
                Integer.parseInt((String)p.get(name+".w")),
                Integer.parseInt((String)p.get(name+".h"))
                );
        c.setVisible(Boolean.valueOf((String)p.get(name+".visible")));
    }
    public static final void loadWindowProperties(Properties p, Window w,String name) {
        w.setBounds(
                Integer.parseInt((String)p.get(name+".x")),
                Integer.parseInt((String)p.get(name+".y")),
                Integer.parseInt((String)p.get(name+".w")),
                Integer.parseInt((String)p.get(name+".h"))
                );
        w.setVisible(Boolean.valueOf((String)p.get(name+".visible")));
    }
    
    public static final Properties loadWindowProperties(String fname,InputStream def) throws IOException {
        Properties p = new Properties();
        if(customWindowProperties(fname)){
            loadWindowProperties(fname,p);
        }
        else
            p.loadFromXML(def);
        return p;
    }
    
    public static final boolean customWindowProperties(String fname) {
        File f = new File(fname+".xml");
        return f.exists();
    }
    public static final void saveWindowProperties(String fname,Properties p)throws IOException {
        File file = new File(fname+".xml");
        FileOutputStream fos = new FileOutputStream(file);
        p.storeToXML(fos,"Window Positions & Sizes");
        fos.close();
    }
    public static final void loadWindowProperties(String fname,Properties p)throws IOException {
        File file = new File(fname+".xml");
        FileInputStream fis = new FileInputStream(file);
        p.loadFromXML(fis);
        fis.close();
    }
}
