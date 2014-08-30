package ranab.util;

/**
 * This is a simple thread pool implementation. We add 
 * <code>Runnable</code> objects and the free thread will
 * pick the object from the queue.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class ThreadPool {
    
    private int miCount;
    private boolean mbStopRequest;
    private Queue mRunnableQueue;
    private PoolThread[] mThreads;
    
    /**
     * Constructor 
     *
     * @param count - number of threads in the thread pool
     */
    public ThreadPool(int count) {
        mThreads = new PoolThread[count];
        mRunnableQueue = new Queue(true);
        for(int i=0; i<mThreads.length; i++) {
            mThreads[i] = new PoolThread(mRunnableQueue);
        }
    }
     
    /**
     * Start pool - start all the threads.
     */
    public synchronized void start() {
        if(mbStopRequest) {
            throw new IllegalStateException("Pool has been stopped");
        }
        for(int i=0; i<mThreads.length; i++) {
            mThreads[i].start();
        }
    }
     
    /**
     * Add a runnable object to be executed.
     */
    public synchronized void add(Runnable rn) {
        if(mbStopRequest) {
            throw new IllegalStateException("Pool has been stopped");
        }
        mRunnableQueue.put(rn);
    }
    
    /**
     * Stop all threads.
     */
    public synchronized void dispose() {
        mbStopRequest = true;
        for(int i=0; i<mThreads.length; i++) {
            mThreads[i].dispose();
        }
        mRunnableQueue.clear();
    }
    
    /**
     * Is the pool stopped.
     */
    public boolean isStopped() {
        return mbStopRequest;
    }
    
    
    //////////////////////////////////////////////////////////////
    /////////////  Inner Class - Thread Pool Thread  /////////////
    //////////////////////////////////////////////////////////////
    class PoolThread extends Thread {
        
        private boolean mbStopRequest;
        private Queue mRunnableQueue;
        
        /**
         * Constructor.
         *
         * @param rnq - the queue holding all the runnable objects.
         */
        public PoolThread(Queue rnq) {
            mRunnableQueue = rnq;
        }
        
        /**
         * Stop this thread.
         */
        public void dispose() {
            mbStopRequest = true;
            interrupt();
        }
        
        /**
         * Check whether the thread has been stopped or not.
         */
        public boolean isStopped() {
            return mbStopRequest;
        }
        
        /**
         * Start this thread.
         */
        public void run() {
            while(!mbStopRequest) {
                Runnable rn = (Runnable)mRunnableQueue.get();
                if(rn == null) {
                    return;
                }
                try {
                    rn.run();
                }
                catch(Throwable ex) {
                    ex.printStackTrace();
                }    
            }
        } 
    }
    
}