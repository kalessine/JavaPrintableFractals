package ranab.util;

import java.util.Comparator;

/**
 * This is default comparator implementation. It compares two
 * <code>java.lang.Comparable</code> objects.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */

public
class DefaultComparator implements Comparator {
    
    /**
     * Compare two comparator objects.
     */
    public int compare(Object o1, Object o2) {
        if( (o1 instanceof java.lang.Comparable) && 
            (o2 instanceof java.lang.Comparable) ) {
           
            Comparable c1 = (Comparable)o1;
            Comparable c2 = (Comparable)o2;
            return c1.compareTo(c2);
        }
        
        throw new IllegalArgumentException("Not java.lang.Comparable");
    } 
}    