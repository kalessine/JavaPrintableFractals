package ranab.util;

import java.util.*;

/**
 * Queue (first in first out) implementation. It supports two types
 * of queues.
 * <li> Queue is empty throws NoSuchElementException.
 * <li> Queue is empty waits for the new element.               
 *
 * Null values <b>cannot</b> be inserted.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */

public
class Queue {
    
    private LinkedList mList = new LinkedList();
    private boolean mbWait;
    private int miMaxSize = 0;
    
    /**
     * Constructor. 
     * @param bWait - thread will wait or not
     */
    public Queue(boolean bWait) {
        mbWait = bWait;
    }
     
    
    /**
     * Try to get the first element. If the list is empty,
     * the thread will wait. If interrupted returns null.
     */
    public synchronized Object get() {
        if(mbWait) {
            while(mList.size() == 0) {
                try {
                   wait();
                }
                catch(InterruptedException ex) {
                    return null;
                }
            }
            return mList.removeLast();
        }
        else {
           return mList.removeLast();
        }
    }
    
    /**
     * Put an object into the queue and notify the waiting thread.
     */
    public synchronized void put(Object obj) {
        if(obj == null) {
            throw new NullPointerException("Queue element cannot be null");
        }
        
        if (miMaxSize <= 0 || mList.size() < miMaxSize) {
            mList.addFirst(obj);
            notifyAll();
        }
    }
    
    /**
     * Get the number of elements in the queue.
     */
    public synchronized int size() {
        return mList.size();
    }
    
    /**
     * Get max size
     */
    public int getMaxSize() {
        return miMaxSize;
    }
    
    /**
     * Set max size
     */
    public void setMaxSize(int maxSize) {
        miMaxSize = maxSize;
    }
    
    /**
     * Is the list empty (size == 0)
     */
    public synchronized boolean isEmpty() {
        return mList.size() == 0;
    }
    
    /**
     * Remove all the elements.
     */
    public synchronized void clear() {
       mList.clear();
    }
}    