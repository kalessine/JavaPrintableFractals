package ranab.util;

import java.util.Comparator;

/**
 * Reverse the sorting order.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
class ReverseComparator implements Comparator {
    
    private Comparator mOrigComp;
    
    /**
     * Constructor.
     * @param comp comparator object to be reversed.
     */
    public ReverseComparator(Comparator comp)  {
        mOrigComp = comp;
    }
    
    /**
     * Compare two objects.
     */
    public int compare(Object o1, Object o2)  {
        return mOrigComp.compare(o2, o1);
    }
    
    /**
     * Compare two reverse comparator objects.
     */
    public boolean equals(Object obj) {
        if( !(obj instanceof ranab.util.ReverseComparator) ) {
            throw new IllegalArgumentException("Not ranab.util.ReverseComparator"); 
        }
        return ((ReverseComparator)obj).mOrigComp.equals(mOrigComp);
    }
}