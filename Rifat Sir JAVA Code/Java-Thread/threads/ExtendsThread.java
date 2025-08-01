class NewThread2 extends Thread {
    NewThread2() {
        super("Extends Thread");
      
        start();
        //run();
    }

    // This is the entry point for the thread.
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Starting child thread.");
        try {
            for (int i = 100; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}

public class ExtendsThread {
    public static void main(String[] args) throws Exception {
        NewThread2 newThread2 = new NewThread2();
        //newThread2.start();
        //newThread2.run();
        for (int i = 50; i > 0; i--) {
            System.out.println("Main Thread: " + i);
            Thread.sleep(15);
        }
    }
}
