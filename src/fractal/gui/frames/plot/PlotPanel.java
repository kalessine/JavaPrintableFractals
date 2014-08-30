/*
 * PlotPanel.java
 *
 * Created on 19 May 2007, 21:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.gui.frames.plot;
import fractal.producer.PrecisionContext;
import fractal.producer.exception.RenderException;
import fractal.producer.result.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;
/**
 *
 * @author Owner
 */
public class PlotPanel extends JPanel {
    
    PixelValue pixel = null;
    List points = null;
    /** Creates a new instance of PlotPanel */
    public PlotPanel() {
    }
    
    public void setPixel(PixelValue pv,List formulas,PrecisionContext ctx,HashMap map,int maxIteration,int forceLoops) {
        pixel = pv;
        List orbittraps = new ArrayList(1);
        CapturePointsOrbitTrap cpot = new CapturePointsOrbitTrap();
        orbittraps.add(cpot);
        try{
        pixel.calculate(formulas,ctx,map,maxIteration,orbittraps,forceLoops);
        }catch(RenderException re) {}
        points = (List)cpot.getTrapValue();
    }
    
    public void paint(Graphics g) {
        
    }
    
    public void plot(Object x,Object y){
    }
    
}
