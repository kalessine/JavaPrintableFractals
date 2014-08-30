/*
 * FormulaCalculator.java
 *
 * Created on 7 March 2007, 20:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.formula;
import fractal.producer.PrecisionContext;
import fractal.producer.calc.ComplexNumber;
import fractal.producer.calc.M;
import fractal.producer.exception.RenderException;
import fractal.producer.orbittrap.OrbitTrap;
import fractal.producer.result.Result;
import fractal.producer.util.HashMapUtil;
import fractal.producer.formula.MergeZValue;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Owner
 */
public class FormulaCalculator {
    public static Result calculate(List formulas,PrecisionContext params,HashMap map,int maxIteration,List orbittraps,ComplexNumber point,ComplexNumber transformed,int forceLoops) throws RenderException {
        if( formulas.size()<1 ) throw new RenderException("Must have a formula!");
        Result result = null;
        map.put(Formula.PIXEL,transformed);
        Formula f = (Formula)formulas.get(0);
        f.init(map,params.getComplexNumberFactory());
        if( orbittraps.size()>0) {
            for(int i=0; i< orbittraps.size(); i++) {
                OrbitTrap o = (OrbitTrap)orbittraps.get(i);
                o.init(map);
            }
        }
        int iterations = 0;
        ComplexNumber lastz = null;
        while((f.bailout(map)&&iterations<maxIteration)|forceLoops!=0){
            map.put(Formula.LASTZ,lastz);
            f.loop(map);
            for(int i=0;i<orbittraps.size();i++) {
                ((OrbitTrap)orbittraps.get(i)).hit(map,iterations);
            }
            iterations+=1;
            if(forceLoops>0)forceLoops--;
            lastz=(ComplexNumber)map.get(Formula.Z);
        }
        boolean in = (iterations==maxIteration);
        result = new Result((ComplexNumber)map.get(Formula.Z),iterations,in);
        if( formulas.size()>1){
            Iterator it = formulas.iterator();
            it.next();
            while(it.hasNext()) {
                MergeFormula merge = (MergeFormula)it.next();
                Formula formula = (Formula)it.next();
                result=merge.mergeFormula(point,transformed,formula,result,params,orbittraps,map);
                //if( iterations != result.getIterations().intValue()) {
                //   System.out.println("Iter="+iterations+" Iter2="+result.getIterations().intValue());
                // }
            }
            
        }
        if( orbittraps.size()>0) {
            for(int i=0; i< orbittraps.size(); i++) {
                OrbitTrap o = (OrbitTrap)orbittraps.get(i);
                result.addTrapValue(o.getTrapValue());
            }
        }
        //System.out.println("Formula Calculator Iteration="+result.getIterations());
        return result;
    }
    static HashMap temp = new HashMap();
    public static Result calculate(Formula formula,PrecisionContext params,HashMap map,int maxIteration,List orbittraps,ComplexNumber value,int forceLoops,MergeConstants mergeConstants) throws RenderException {
        MergeZValue mergeZ = MergeZValue.RESULT_CONSTANT;
        Result result = null;
        Formula f = formula;
        //f.init(map,params.getComplexNumberFactory());
        if( orbittraps.size()>0) {
            for(int i=0; i< orbittraps.size(); i++) {
                OrbitTrap o = (OrbitTrap)orbittraps.get(i);
                o.init(map);
            }
        }
        Iterator it = map.keySet().iterator();
        while(it.hasNext()) {
            String name = (String)it.next();
            Object o = map.get(name);
            map.put(name,mergeConstants.processConstant(o));
        }
        temp.clear();
        temp.put(Formula.PIXEL,value);
        f.init(temp,params.getComplexNumberFactory());
        if( mergeConstants!=MergeConstants.NEW_CONSTANTS){
            HashMapUtil.removeSecondKeysFromFirstMap(temp,map);
        }
        map.putAll(temp);
        if( mergeZ != MergeZValue.RESULT_CONSTANT){
           map.put(Formula.PIXEL,value);
           map.put(Formula.Z,mergeConstants.processConstant(value));
        }
        //map.put(Formula.Z,z);
        int iterations = 0;        
        int iter = 0;
        int fl = forceLoops;
        ComplexNumber lastz = null;
        while((formula.bailout(map)&&iter<maxIteration)||fl!=0){
            map.put(Formula.LASTZ,lastz);
            formula.loop(map);
            for(int i=0;i<orbittraps.size();i++) {
                ((OrbitTrap)orbittraps.get(i)).hit(map,iter);
            }
            iter++;
            if(fl>0)fl--;
            lastz=(ComplexNumber)map.get(Formula.Z);
        }
        
        iterations=iter;
        boolean in = (iterations==maxIteration);
        result = new Result((ComplexNumber)map.get(Formula.Z),iterations,in);
        // We Dont Need To Do This For Merge Formulas
        /*
        if( orbittraps.size()>0) {
            for(int i=0; i< orbittraps.size(); i++) {
                OrbitTrap o = (OrbitTrap)orbittraps.get(i);
                result.addTrapValue(o.getTrapValue());
            }
        }
         **/
        //System.out.println("Formula Calculator Iteration="+result.getIterations());
        return result;
    }
    public static Result _calculate(HashMap map, Formula formula, MergeIterations mergeIterations,MergeResult mergeResult,List orbittraps,Result result,ComplexNumber point,int maxIterations,PrecisionContext context,int forceLoops,MergeIn mergeIn){
        //HashMap temp = new HashMap();
        //temp.put(Formula.PIXEL,point);
        //formula.init(temp,context.getComplexNumberFactory());
        //HashMapUtil.removeSecondKeysFromFirstMap(temp,map);
        //map.putAll(temp);
        //map.put(Formula.PIXEL,point);
        int iter = 0;
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
        boolean oldIn = result.isIn();
        if( mergeIn.toInt() == MergeIn.MERGE_IN_FIRST ) newIn=oldIn;
        if( mergeIn.toInt() == MergeIn.MERGE_IN_SECOND ) newIn=in;
        if( mergeIn.toInt() == MergeIn.MERGE_IN_FIRST_AND_SECOND ) newIn=in&&oldIn;
        if( mergeIn.toInt() == MergeIn.MERGE_IN_FIRST_OR_SECOND ) newIn=in||oldIn;
        if( mergeIn.toInt() == MergeIn.MERGE_IN_FIRST_XOR_SECOND ) newIn= in^oldIn;
        return new Result(Z,iterations,newIn);
    }
}
