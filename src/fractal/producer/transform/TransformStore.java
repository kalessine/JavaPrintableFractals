/*
 * TransformStore.java
 *
 * Created on 26 February 2007, 10:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.transform;

import fractal.producer.transform.builtin.InsideOutTransform;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Owner
 */
public class TransformStore {
    static List TRANSFORMS = new ArrayList(0);
    /** Creates a new instance of TransformStore */
    private TransformStore() {
    }
    public static List getTransforms() { return TRANSFORMS; }
    public static int getSize() { return TRANSFORMS.size(); }
    public static Transform getTransform(int id) { return (Transform)TRANSFORMS.get(id); }
    public static void loadTransform(Transform xform) {
       TRANSFORMS.add(xform);
    }
    public static void unloadTransform(Transform xform) {
       TRANSFORMS.remove(xform);
    }
    static{
        TRANSFORMS.add(new InsideOutTransform());
    }
}
