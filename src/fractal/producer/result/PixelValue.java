/*
 * PixelValue.java
 *
 * Created on 8 February 2007, 21:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.result;

import fractal.producer.calc.ComplexNumber;
import fractal.producer.colour.ColourMap;
import fractal.producer.PrecisionContext;
import fractal.producer.calc.ComplexDoubleFactory;
import fractal.producer.colour.Colouring;
import fractal.producer.transform.Transform;
import fractal.producer.exception.*;
import fractal.producer.formula.FormulaCalculator;
import fractal.producer.orbittrap.OrbitTrap;
import java.awt.Color;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Owner
 */
public class PixelValue implements Externalizable {
    public static final ComplexDoubleFactory COMPLEX_FACTORY = new ComplexDoubleFactory();
    // Starting Value, Everything Is Derived from This
    private ComplexNumber value = null;
    // The Transformed Pixel Value
    private ComplexNumber transformed = null;
    // The Default Formula
    // Formula Parameters
    // The Calculated Result
    private Result result = null;
    // The Ending Colour
    private Color colour = null;
    
    /** Creates a new instance of PixelValue */
    
    public PixelValue() {        
    }

    public PixelValue(ComplexNumber c) {
        this.setValue(c);
    }
    public boolean isTransformed() {
        return getTransformed() != null;
    }
    public void transform(List transforms,HashMap map) {
        Iterator it = transforms.iterator();
        transformed=value;
        while(it.hasNext()) {
            Transform xf = (Transform)it.next();
            transformed=xf.transform(transformed,map);
        }
    }
    
    public boolean isCalculated() {
        return this.getResult()==null;
    }
    public void calculate(List formulas,PrecisionContext params,HashMap map,int maxIteration,List orbittraps,int forceLoops) throws RenderException {
        setResult(FormulaCalculator.calculate(formulas,params,map,maxIteration,orbittraps,getValue(),getTransformed(),forceLoops));
    }
    public boolean isColoured() {
        return getColour()==null;
    }
    public void colour(List colourings,ColourMap cmap,HashMap map,Color background) {
        map.put(OrbitTrap.ORBIT_INDEX,0);
        if( colourings.size()==1) {
            Colouring c = (Colouring)colourings.get(0);
            setColour(c.getColour(this,cmap,background,map));
        }else {
            Iterator it = colourings.iterator();
            Color col = background;
            int orbitindex = 0;
            while(it.hasNext()) {
                Colouring c = (Colouring)it.next();
                col = c.getColour(this,cmap,col,map);
                if( c instanceof OrbitTrap) {
                    orbitindex+=1;
                    map.put(OrbitTrap.ORBIT_INDEX,orbitindex);
                }
            }
            setColour(col);
        }
    }
    protected void finalize() throws Throwable {
    }
    
    public Result getResult() {
        return result;
    }
    
    public void setResult(Result result) {
        this.result = result;
    }
    
    public Color getColour() {
        return colour;
    }
    
    public void setColour(Color colour) {
        this.colour = colour;
    }
    
    public ComplexNumber getValue() {
        return value;
    }
    
    public void setValue(ComplexNumber value) {
        this.value = value;
    }
    
    public ComplexNumber getTransformed() {
        return transformed;
    }
    
    public void setTransformed(ComplexNumber transformed) {
        this.transformed = transformed;
    }
    public void readExternal(ObjectInput in)throws IOException, ClassNotFoundException{
        String s = in.readUTF();
        int p = in.readInt();
        try{
            if(s!=null&&!"".equals(s))
                value = COMPLEX_FACTORY.parse(s,p);
            s = in.readUTF();
            p = in.readInt();
            if(s!=null&&!"".equals(s))
                transformed = COMPLEX_FACTORY.parse(s,p);
        }catch(ParseException pe) {
            throw new IOException("Cant Parse-"+pe);
        }
        result = (Result)in.readObject();
        colour = new Color(in.readInt());
    }
    public void writeExternal(ObjectOutput out)throws IOException{
        if( value!=null){
            out.writeUTF(value.toString());
            out.writeInt(value.getPrecision());
        } else {
            out.writeUTF("");
            out.writeInt(0);
        }
        if( transformed!=null){
            out.writeUTF(transformed.toString());
            out.writeInt(transformed.getPrecision());
        }else {
            out.writeUTF("");
            out.writeInt(0);
        }
        ((ObjectOutputStream)out).writeUnshared(result);
        if( colour != null )
            out.writeInt(colour.getRGB());
        else out.writeInt(0);
    }
}
