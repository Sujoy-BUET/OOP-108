package synchronization;

import java.util.ArrayList;
import java.util.List;

class MyThread implements Runnable {
    List<String> list;
    Thread t;

    public MyThread(List<String> list) {
        this.list = list;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        for (int i = 1; i <= 10000; i++) 
        {
            //list.add(String.valueOf(i));
            synchronized(list) 
            {
                list.add(String.valueOf(i));
            }
        /* 
            Java collections like ArrayList are not synchronized by default. When multiple threads invoke add() without synchronization:
            - They might both check the internal array’s size simultaneously
            - Decide there’s room
            - Then both try to insert at the same index
            It’s a classic concurrent modification hazard.
            ✅ Safer Alternatives
            Instead of synchronizing manually, you could also use a thread-safe collection like:
            - Collections.synchronizedList(new ArrayList<>())
            - CopyOnWriteArrayList (if reads far outweigh writes)
            - ConcurrentLinkedQueue (if you need queue-like behavior)

            Let me know if you want to simulate this with multiple threads or dive into debugging the unsynchronized version! Happy to walk through it.
        */
        }
    }
}

public class TestSyncArrayList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        MyThread myThread1 = new MyThread(list);
        MyThread myThread2 = new MyThread(list);
        try {
            myThread1.t.join();
            myThread2.t.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(list.size());
    }
}
