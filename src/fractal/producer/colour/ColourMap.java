/*
 * ColourMap.java
 *
 * Created on 3 February 2007, 18:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.colour;

/**
 *
 * @author Owner
 */
import java.util.*;
import java.awt.Color;
import fractal.producer.colour.ColourItem;
import java.io.*;
public class ColourMap implements Serializable {
    private List colouritems = new ArrayList();
    private double totalSize = 0d;
    List colourmap = new ArrayList();
    private int mapSize = 255;
    private double repeatColourMap = 2;
    private double offset = 0d;
    
    public ColourMap() {
        getColourItems().add(new ColourItem(Color.RED,1d));
        getColourItems().add(new ColourItem(Color.ORANGE,1d));
        getColourItems().add(new ColourItem(Color.YELLOW,1d));
        getColourItems().add(new ColourItem(Color.GREEN,1d));
        ColourItem ci = new ColourItem(Color.BLUE,1d);
        ci.setStripeColor(Color.BLACK);
        ci.setBlend(ColourItem.LEFT_BLEND);
        getColourItems().add(ci);
        ci = new ColourItem(Color.MAGENTA,1d);
        ci.setStripeColor(Color.BLACK);
        ci.setBlend(ColourItem.RIGHT_BLEND);
        getColourItems().add(ci);
        getColourItems().add(new ColourItem(Color.WHITE,1d));
        getColourItems().add(new ColourItem(Color.GRAY,1d));
        recalculateLength();
        buildColourMap();
    }
    
    public Color getColour(Number id) {
        if( id instanceof Integer ) {
            int i = id.intValue();
            i=Math.abs(i);
            i=i%getMapSize();
            return (Color)colourmap.get(i);
        } else if( id instanceof Double ) {
            double d = id.doubleValue();
            d = Math.abs(d);
            d = d%getMapSize();
            Color left = (Color)colourmap.get((int)Math.floor(d));
            Color right = (Color)colourmap.get( (int)Math.ceil(d) );
            return ColorUtil.blend(left,right,1-(d%1));
        } else {
            System.out.println("Unsupported Number:"+id);
            throw new UnsupportedOperationException();
        }
    }
    public void addColourItem(ColourItem ci) {
        colouritems.add(ci);
        recalculateLength();
    }
    public void removeColourItem(ColourItem ci) {
        colouritems.remove(ci);
        recalculateLength();
    }
    public ColourItem getColourItem(int id) {
        return (ColourItem)colouritems.get(id);
    }
    public void recalculateLength(){
        Iterator it = getColourItems().iterator();
        double total = 0d;
        while(it.hasNext()){
            ColourItem ci = (ColourItem)it.next();
            ci.setLeftValue(total);
            total+=ci.getLength();
        }
        totalSize=total;
        it = getColourItems().iterator();
        
        // The first colour starts here!
        double place = (totalSize-offset)%totalSize;
        while(it.hasNext()){
            ColourItem ci = (ColourItem)it.next();
            ci.setColourCenter(place);
            place+=ci.getLength();
            place = place % totalSize;
        }
    }
    public synchronized void buildColourMap() {
        colourmap.clear();
        double position = getOffset();
        double step = getRepeatColourMap()*totalSize/(double)(getMapSize());
        for(int i=0; i<(mapSize+1);i++){
            //System.out.println("Position="+position+" Step="+step);
            Color c = getColourAt(position,step);
            colourmap.add(c);
            position+=step;
        }
    }
    public Color getColourAt(double pos, double step) {
        //System.out.println("Pos1="+pos);
        pos = pos % getTotalSize();
        ColourItem leftColour = findLeftColour(pos);
        ColourItem rightColour = findRightColour(pos);
        if( leftColour.isStriped() ) {
            boolean stripe = Math.round(((pos-leftColour.getLeftValue())/step))%2==1;
            if(stripe){
                if( leftColour.getBlend() == ColourItem.LEFT_BLEND) {
                    double percentleft = (pos-leftColour.getLeftValue())/leftColour.getLength();
                    Color c = ColorUtil.blend(leftColour.getColor(),leftColour.getStripeColor(),1-percentleft);
                    return c;
                } else if( leftColour.getBlend() == ColourItem.RIGHT_BLEND) {
                    double percentleft = (pos-leftColour.getLeftValue())/leftColour.getLength();
                    Color c = ColorUtil.blend(leftColour.getStripeColor(),rightColour.getColor(),1-percentleft);
                    return c;
                } else if( leftColour.getBlend() == ColourItem.CRISS_CROSS_BLEND ) {
                    double percentleft = (pos-leftColour.getLeftValue())/leftColour.getLength();
                    Color c = ColorUtil.blend(leftColour.getColor(),rightColour.getColor(),percentleft);
                    return c;
                }
                double percentleft = (pos-leftColour.getLeftValue())/leftColour.getLength();
                Color col = ColorUtil.blend(leftColour.getColor(),rightColour.getColor(),1-percentleft);
                if( leftColour.getBlend()== ColourItem.DOUBLE_BLEND) {
                    double blend = 0.5d - percentleft;
                    if( blend < 0.0d){
                        Color c = ColorUtil.blend(leftColour.getColor(),leftColour.getStripeColor(),Math.abs(blend));
                        return c;
                    }else {
                        Color c = ColorUtil.blend(rightColour.getColor(),leftColour.getStripeColor(),blend);
                        return c;
                    }
                } else if( leftColour.getBlend() == ColourItem.ONE_QUARTER_BLEND ) {
                    Color c = ColorUtil.blend(col,leftColour.getStripeColor(),0.25d);
                    return c;
                } else if( leftColour.getBlend() == ColourItem.HALF_BLEND ) {
                    Color c = ColorUtil.blend(col,leftColour.getStripeColor(),0.5d);
                    return c;
                } else if( leftColour.getBlend() == ColourItem.THREE_QUARTER_BLEND ) {
                    Color c = ColorUtil.blend(col,leftColour.getStripeColor(),0.75d);
                    return c;
                } else
                    return leftColour.getStripeColor();
            }
        }
        double percentleft = (pos-leftColour.getLeftValue())/leftColour.getLength();
        Color c = ColorUtil.blend(leftColour.getColor(),rightColour.getColor(),1-percentleft);
        return c;
    }
    public ColourItem findRightColour(double pos) {
        pos = pos % getTotalSize();
        ColourItem right = (ColourItem)getColourItems().get(0);
        for(int i=getColourItems().size()-1;i>-1;i--) {
            ColourItem ci = (ColourItem)getColourItems().get(i);
            if( (ci.getLeftValue()<=pos) && (ci.getRightValue() >= pos) ){
                return right;
            }
            right = ci;
        }
        System.out.println("Can't Find right Colour Position"+pos);
        return null;
    }
    public ColourItem findLeftColour(double pos) {
        pos = pos % getTotalSize();
        double p = 0d;
        for(int i=0;i<getColourItems().size();i++) {
            ColourItem ci = (ColourItem)getColourItems().get(i);
            if( ci.getLeftValue()<=pos && ci.getRightValue() >= pos ){
                return ci;
            }
        }
        // Colour Is The Last Colour In The List.
        return (ColourItem)getColourItems().get(colouritems.size()-1);
    }
    
    public List getColourItems() {
        return colouritems;
    }
    
    public double getTotalSize() {
        return totalSize;
    }
    
    public int getMapSize() {
        return mapSize;
    }
    
    public void setMapSize(int mapsize) {
        this.mapSize = mapsize;
        this.buildColourMap();
    }
    
    public double getRepeatColourMap() {
        return repeatColourMap;
    }
    
    public void setRepeatColourMap(double repeatColourMap) {
        this.repeatColourMap = repeatColourMap;
        this.buildColourMap();
    }
    
    public double getOffset() {
        return offset;
    }
    
    public void setOffset(double offset) {
        this.offset = offset;
        this.recalculateLength();
        this.buildColourMap();
    }
    public void save(File f) throws IOException {
    }
    public void load(File f)throws IOException  {}
    public void dumpMap() {
        System.out.println("Dumped Map");
        for(int i=0;i<colouritems.size();i++){
            System.out.println(colouritems.get(i).toString());
        }
    }
}