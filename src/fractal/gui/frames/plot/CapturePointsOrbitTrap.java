/*
 * CapturePointsOrbitTrap.java
 *
 * Created on 19 May 2007, 21:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.gui.frames.plot;
import fractal.producer.orbittrap.*;
import java.util.*;
import fractal.producer.formula.*;
import fractal.producer.colour.builtin.*;
import fractal.producer.colour.*;
/**
 *
 * @author Owner
 */
public class CapturePointsOrbitTrap extends AbstractColouring implements OrbitTrap {    
    /** Creates a new instance of CapturePointsOrbitTrap */
    public CapturePointsOrbitTrap() {
    }
    List points = null;
    public void init(HashMap map){
       points= new ArrayList(0);
    }
    public void hit(HashMap map,Number iterations){
       points.add(map.get(Formula.Z));
    }
    public Object getTrapValue(){
       return points;
    }  
    public Colouring clone() { return this; }
}