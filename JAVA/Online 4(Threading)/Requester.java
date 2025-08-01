//
// Job Requester
//
import java.util.Queue;

public class Requester extends Thread {
    // For giving unique id to each new work item
    static private Integer workItemId = 0;

    // Queue of work items.
    private Queue<WorkItem> queue = null;

    // Whether termination of the Requester has been signalled
    private boolean fQuit = false;
   
    public Requester(Queue<WorkItem> queue, String name) {
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
        // Requester thread begins.
        //
        // TODO: Run an infinite loop.
        // If shutdown has been signalled, then we break the infinite loop
        //
        // Otherwise, create a new workitem. Use queue.add method to enqueue it.
        // The underlying queue is not threadsafe. So we need to take appropriate measure(s). 
		//
		// Afterwards, we go to sleep for 100 ms.
        //
        while (true) {
            if(fQuit==true) break;
			//
			// If the work was successfully added, we write a log statement.
			//
            WorkItem workitem = new MTAWorkItem(workItemId);
            workItemId++; 
            synchronized (queue) {
                queue.add(workitem);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
			System.out.println("" + Thread.currentThread().getName() + ">> Added work item: " + workitem.getId());

            //new MTAWorkItem(workItemIWorkItem workitem =  new MTAWorkItem(workItemId);
        }
        }
}
