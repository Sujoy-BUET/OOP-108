//
// An implementation of WorkItem interface
// Students need not check or change this code.
//

import java.util.Random;

public class MTAWorkItem implements WorkItem {
    private int id;
    static Random rng = new Random(0);

    MTAWorkItem(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public boolean work() {
        try {
            Thread.sleep(rng.nextInt(50)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
