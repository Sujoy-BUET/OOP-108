//
// Job Executor 
//
// Do not remove any line of code
// Follow the TODO remark and write your code
//

import java.util.Queue;

public class Executor extends Thread {
    // Queue of work items.
    private Queue<WorkItem> queue = null;

    // Whether termination of the Executor has been signalled.
    private boolean fQuit = false;
    
    public Executor(Queue<WorkItem> queue, String name) {
        super(name);
        this.queue = queue;
    }

    public void signalShutDown() {
        //
        // TODO: Record that shutdown has been signalled.
        //
        fQuit = true;
    }

    public void run() {
        //
        // Executor thread begins
        //
        // TODO: Run an infinite loop. If there is work to do, we perform it
        // Otherwise, we go to sleep for a second and try again. However, 
        // if shutdown has been signalled, then we break the infinite loop 
		// 
		// To extract a workitem from queue, use queue.poll(). If there is
		// nothing in the queue, it will return null. Else, it will return the
		// reference to a work item to be executed.
		//
		// The underlying queue is not threadsafe. So we need to
        // take appropriate measure(s).
        //
        while (true) {
			//
			// If the work was successfully performed, we write a log statement.
			//
            if(fQuit==true) break;
            WorkItem workitem;
            synchronized (queue) {
                 workitem = queue.poll();

            }
            if(workitem==null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }
			System.out.println("" + Thread.currentThread().getName() + ">> Finished work item: " + workitem.getId());
        }
        
    }
}
