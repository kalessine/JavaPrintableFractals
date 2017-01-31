/*
 * ColouringStore.java
 *
 * Created on 4 March 2007, 22:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.colour;

import fractal.producer.colour.builtin.AdjustableStripeColouring;
import fractal.producer.colour.builtin.AngleToOriginColouring;
import fractal.producer.colour.builtin.DeltaAngleColouring;
import fractal.producer.colour.builtin.DeltaAngleModIterationsColouring;
import fractal.producer.colour.builtin.DeltaAngleOnIterationsColouring;
import fractal.producer.colour.builtin.ImagColouring;
import fractal.producer.colour.builtin.InColouring;
import fractal.producer.colour.builtin.IterationsColouring;
import fractal.producer.colour.builtin.LogTenIterationsColouring;
import fractal.producer.colour.builtin.ModSquaredColouring;
import fractal.producer.colour.builtin.NaturalLogIterationsColouring;
import fractal.producer.colour.builtin.RealColouring;
import fractal.producer.colour.builtin.RonRISquaredColouring;
import fractal.producer.orbittrap.builtin.AngleToPointOrbitTrap;
import fractal.producer.orbittrap.builtin.BitmapOrbitTrap;
import fractal.producer.orbittrap.builtin.DistanceTravelledOrbitTrap;
import fractal.producer.orbittrap.builtin.OrbitCountingOrbitTrap;
import fractal.producer.orbittrap.builtin.SimpleOrbitTrap;
import fractal.producer.orbittrap.builtin.SmallestAngleOrbitTrap;
import fractal.producer.orbittrap.builtin.StephenOrbitTrap1;
import fractal.producer.orbittrap.builtin.StephenOrbitTrap1a;
import fractal.producer.orbittrap.builtin.StephenOrbitTrap1b;
import fractal.producer.orbittrap.builtin.StephenOrbitTrap1b_1;
import fractal.producer.orbittrap.builtin.SumOfAngleOrbitTrap;
import fractal.producer.orbittrap.builtin.SumOfImagOrbitTrap;
import fractal.producer.orbittrap.builtin.SumOfRIOrbitTrap;
import fractal.producer.orbittrap.builtin.SumOfRealOrbitTrap;
import fractal.producer.orbittrap.builtin.SumOfSquaresOrbitTrap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Owner
 */
public class ColouringStore {
    static List COLOURINGS = new ArrayList(0);
    /** Creates a new instance of ColouringStore */
    public ColouringStore() {
    }
    public static List getColourings() { return COLOURINGS; }
    public static int getSize() { return COLOURINGS.size(); }
    public static Colouring getColouring(int id) { return (Colouring)COLOURINGS.get(id); }
    public static void loadColouring(Colouring c) {
        COLOURINGS.add(c);
    }
    public static void unloadColouring(Colouring c) {
        COLOURINGS.remove(c);
    }
    static{
        COLOURINGS.add(new RGBtoCMYKColour());
        COLOURINGS.add(new IterationsColouring());
        COLOURINGS.add(new RealColouring());
        COLOURINGS.add(new ImagColouring());
        COLOURINGS.add(new ModSquaredColouring());
        COLOURINGS.add(new AngleToOriginColouring());
        COLOURINGS.add(new AdjustableStripeColouring());
        COLOURINGS.add(new RonRISquaredColouring());
        COLOURINGS.add(new SimpleOrbitTrap());
        COLOURINGS.add(new AngleToPointOrbitTrap());
        COLOURINGS.add(new SmallestAngleOrbitTrap());
        COLOURINGS.add(new OrbitCountingOrbitTrap());
        COLOURINGS.add(new StephenOrbitTrap1());
        COLOURINGS.add(new StephenOrbitTrap1a());
        COLOURINGS.add(new StephenOrbitTrap1b());
        COLOURINGS.add(new StephenOrbitTrap1b_1());
        COLOURINGS.add(new BitmapOrbitTrap());
        COLOURINGS.add(new DistanceTravelledOrbitTrap());
        COLOURINGS.add(new SumOfSquaresOrbitTrap());
        COLOURINGS.add(new SumOfRealOrbitTrap());
        COLOURINGS.add(new SumOfImagOrbitTrap());
        COLOURINGS.add(new SumOfAngleOrbitTrap());
        COLOURINGS.add(new SumOfRIOrbitTrap());
        COLOURINGS.add(new DeltaAngleColouring());
        COLOURINGS.add(new DeltaAngleOnIterationsColouring());
        COLOURINGS.add(new DeltaAngleModIterationsColouring());
        COLOURINGS.add(new LogTenIterationsColouring());
        COLOURINGS.add(new NaturalLogIterationsColouring());
        COLOURINGS.add(new InColouring());
        COLOURINGS.add(new DeutanColour());
    }
}
