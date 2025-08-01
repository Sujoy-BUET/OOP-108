//
// An interface representing a work item
//
public interface WorkItem {
    //
    // An unique integer identifier for the work item
    //
    public int getId();
    
    //
    // Perform the work. If the work is successfully completed
    // true is returned.
    //
    public boolean work();
}
