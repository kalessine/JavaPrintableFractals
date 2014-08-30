/*
 * Result.java
 *
 * Created on 8 February 2007, 21:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.result;

import fractal.producer.calc.ComplexNumber;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Owner
 */
public class Result implements Externalizable {
    Number iterations = null;
    ComplexNumber Z = null;
    List trapValues = null;
    private boolean in = false;

//    HashMap results = null;
    /** Creates a new instance of Result */
    public Result(){}
    public Result(ComplexNumber z, Number iter,boolean in) {
        this.Z=z;
        this.iterations=iter;
        this.in=in;
    }
 /*   public Result(ComplexNumber z, Number iter,HashMap res) {
        this.Z=z;
        this.iterations=iter;
        this.results=res;
    }
  **/
    /*
    public HashMap getResults() {
        return results;
    }*/
    public Number getIterations() { return iterations; }
    public ComplexNumber getZ() { return Z; }
    //public Object getResult(String s) { return results.get(s); }
    public Object getTrapValue(int id) { 
       if( trapValues ==null) return null;
       return trapValues.get(id);
    }
    public void addTrapValue(Object o) { 
       if(trapValues==null) trapValues=new ArrayList(1);
       trapValues.add(o);
    }

    public void readExternal(ObjectInput in)throws IOException, ClassNotFoundException{
        iterations = in.readInt();
        Z = (ComplexNumber)in.readObject();
        trapValues = (List)in.readObject();
        
    }
    public void writeExternal(ObjectOutput out)throws IOException{
         out.writeInt(iterations.intValue());
         out.writeObject(Z);
         out.writeObject(trapValues);
    }

    public boolean isIn() {
        return in;
    }

    public void setIn(boolean in) {
        this.in = in;
    }
}
