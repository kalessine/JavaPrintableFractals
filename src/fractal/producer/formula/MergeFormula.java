/*
 * MergeFomula.java
 *
 * Created on 4 March 2007, 18:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.formula;

import fractal.producer.PrecisionContext;
import fractal.producer.calc.ComplexNumber;
import fractal.producer.calc.M;
import fractal.producer.exception.RenderException;
import fractal.producer.result.Result;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Owner
 */
public class MergeFormula implements Serializable {
    private MergeZValue mergeZ = MergeZValue.RESULT;
    MergeIterations mergeIterations = MergeIterations.ADDITIVE;
    MergeResult mergeResult = MergeResult.SECOND;
    private MergeIn mergeIn = MergeIn.FIRST_AND_SECOND;
    private MergeConstants mergeConstants = MergeConstants.KEEP_CONSTANTS;
    int maxIterations = 125;
    int forceLoops = 0;
    public MergeFormula() {
        
    }
    public MergeIterations getMergeIterations() { return mergeIterations; }
    public void setMergeIterations(MergeIterations mi) { mergeIterations=mi; }
    public MergeResult getMergeResult() { return mergeResult; }
    public void setMergeResult(MergeResult mr) { mergeResult=mr; }
    public String toString() { return "Merge Formula Settings"; }
    public Result mergeFormula(ComplexNumber point,ComplexNumber transformed,Formula formula,Result oldResult,PrecisionContext context,List orbittraps,HashMap map) throws RenderException {
        ComplexNumber value = point;
        ComplexNumber z = (ComplexNumber)map.get(Formula.Z);
        if( mergeZ == MergeZValue.PIXEL ) {
            value=point;
            z=point;
        }
        if( mergeZ == MergeZValue.TRANSFORMED ) {
            value=transformed;
            z=transformed;
        }
        if( mergeZ == MergeZValue.RESULT ) {
            value=oldResult.getZ();
            z=oldResult.getZ();
        }
        if( mergeZ == MergeZValue.RESULT_CONSTANT ) {
            // Keep The Old PixelValue Constant!
            value=oldResult.getZ();
            z=oldResult.getZ();
        }
        HashMap _map = map;
        map.put(Formula.Z,z);
        Result nResult = FormulaCalculator.calculate(formula,
                context,
                _map,
                this.maxIterations,
                orbittraps,
                value,
                this.forceLoops,
                mergeConstants
                ); 
        ComplexNumber Z = oldResult.getZ();
        ComplexNumber nZ = nResult.getZ();
        Number iterations = nResult.getIterations();
        boolean newIn = nResult.isIn();
        boolean oldIn = oldResult.isIn();
        if( mergeIterations == MergeIterations.ADDITIVE )iterations= M.add(iterations,oldResult.getIterations(),context.getMathContext());
        if( mergeIterations == MergeIterations.SUBTRACTIVE ) {
            iterations=M.subtract(oldResult.getIterations(),iterations,context.getMathContext());
        }
        if( mergeIterations == MergeIterations.FIRST ) iterations = oldResult.getIterations();
        if( mergeResult == MergeResult.ADDITIVE ) Z=Z.add(nZ);
        if( mergeResult == MergeResult.SUBTRACTIVE ) Z=Z.subtr(nZ);
        if( mergeResult == MergeResult.SECOND ) Z=nZ;
        if( mergeIn == MergeIn.FIRST ) newIn=oldIn;
        if( mergeIn == MergeIn.SECOND ) newIn=nResult.isIn();
        if( mergeIn == MergeIn.FIRST_AND_SECOND ) newIn=nResult.isIn()&&oldIn;
        if( mergeIn == MergeIn.FIRST_OR_SECOND ) newIn=nResult.isIn()||oldIn;
        if( mergeIn == MergeIn.FIRST_XOR_SECOND ) newIn= nResult.isIn()^oldIn;
        return new Result(Z,iterations,newIn);

 }
    public int getMaxIteration() { return maxIterations; }
    public void setMaxIteration(int i) { maxIterations=i; }
    public int getForceLoops() { return forceLoops; }
    public void setForceLoops(int i) {
        if(i>-1)
            forceLoops=i;
    }
    
    public MergeIn getMergeIn() {
        return mergeIn;
    }
    
    public void setMergeIn(MergeIn mergeIn) {
        this.mergeIn = mergeIn;
    }
    /*
        boolean oldIn = result.isIn();
        temp.put(Formula.PIXEL,point);
        formula.init(temp,context.getComplexNumberFactory());
        HashMapUtil.removeSecondKeysFromFirstMap(temp,map);
     
        map.putAll(temp);
        map.put(Formula.PIXEL,point);
        int iter = 0;
        if( mergeIterations==MergeIterations.CHAIN) iter = result.getIterations().intValue();
        int f = forceLoops;
        while((formula.bailout(map)&&iter<maxIterations)||f!=0){
            formula.loop(map);
            for(int i=0;i<orbittraps.size();i++) {
                ((OrbitTrap)orbittraps.get(i)).hit(map,iter);
            }
            iter++;
            if(f>0)f--;
        }
        Number iterations = iter;
        boolean in = iter==maxIterations;
        ComplexNumber Z = result.getZ();
        ComplexNumber nZ = (ComplexNumber)map.get(Formula.Z);
        if( mergeIterations.toInt() == MergeIterations.MERGE_ITERATIONS_ADDITIVE )iterations= M.add(iterations,result.getIterations(),context.getMathContext());
        if( mergeIterations.toInt() == MergeIterations.MERGE_ITERATIONS_SUBTRACTIVE ) {
            iterations=M.subtract(result.getIterations(),iterations,context.getMathContext());
            //System.out.println("Merge Formula Iters = "+iterations);
        }
        if( mergeIterations.toInt() == MergeIterations.MERGE_ITERATIONS_FIRST ) iterations = result.getIterations();
        if( mergeResult.toInt() == MergeResult.MERGE_RESULT_ADDITIVE ) Z=Z.add(nZ);
        if( mergeResult.toInt() == MergeResult.MERGE_RESULT_SUBTRACTIVE ) Z=Z.subtr(nZ);
        if( mergeResult.toInt() == MergeResult.MERGE_RESULT_SECOND ) Z=nZ;
        boolean newIn = in;
        if( mergeIn.toInt() == MergeIn.MERGE_IN_FIRST ) newIn=oldIn;
        if( mergeIn.toInt() == MergeIn.MERGE_IN_SECOND ) newIn=in;
        if( mergeIn.toInt() == MergeIn.MERGE_IN_FIRST_AND_SECOND ) newIn=in&&oldIn;
        if( mergeIn.toInt() == MergeIn.MERGE_IN_FIRST_OR_SECOND ) newIn=in||oldIn;
        if( mergeIn.toInt() == MergeIn.MERGE_IN_FIRST_XOR_SECOND ) newIn= in^oldIn;
        //System.out.println("Merge Formula Iters 2 = "+iterations);
     
     
     */
    
    public MergeConstants getMergeConstants() {
        return mergeConstants;
    }
    
    public void setMergeConstants(MergeConstants mergeConstants) {
        this.mergeConstants = mergeConstants;
    }
    public MergeZValue getMergeZValue() {
        return mergeZ;
    }
    
    public void setMergeZValue(MergeZValue mergePixel) {
        this.mergeZ = mergePixel;
    }
    
    
    
}
