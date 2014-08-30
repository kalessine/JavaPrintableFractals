/*
 * HashMapUtil.java
 *
 * Created on 7 March 2007, 20:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fractal.producer.util;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Owner
 */
public class HashMapUtil {
    
    /** Creates a new instance of HashMapUtil */
    private HashMapUtil() {
    }
    public static void removeSecondKeysFromFirstMap(HashMap first,HashMap second) {
         Iterator it = second.keySet().iterator();
         while(it.hasNext()) {
            Object o = it.next();
            if( first.get(o) !=null) first.remove(o);
         }
    }
}
