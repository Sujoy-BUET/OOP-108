//package threads;

class NewThread1 implements Runnable {
    Thread t;

    NewThread1() {
        // here you must create a new thread and pass this instance of the class to it.
        t = new Thread(this);
        t.start();
        // new Thread(this).start(); 
    }

    // This is the entry point for the thread.
    @Override
    public void run() {
        try {
            for (int i = 100; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}

public class ImplementsThread {
    public static void main(String[] args) throws Exception {
        new NewThread1();
        for (int i = 500; i > 0; i--) {
            System.out.println("Main Thread: " + i);
            Thread.sleep(200);
        }
    }
}
