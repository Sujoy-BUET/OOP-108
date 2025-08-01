class MyThread implements Runnable{
    Thread t;
    int threadno;
    public MyThread(int num){
        threadno = num;
        // new Thread(this,"Thread "+threadno).start();
        t = new Thread(this,"My Thread "+threadno);
        t.start(); // run method will be called automatically
    }
    @Override
    public void run(){
        for(int i=5;i>=0;i--){
            System.out.println(threadno+"st Child Thread: " + i);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(threadno+" Child Thread finished execution.");
    }
}

public class RunnableThread {
    public static void main(String[] args) {
        System.out.println("Main Thread started");
        MyThread ob1 =  new MyThread(1);
        MyThread ob2 =  new MyThread(2);
        System.out.println("Is ob1 thread alive? " + ob1.t.isAlive());
        System.out.println("Is ob2 thread alive? " + ob2.t.isAlive());
        try {
            ob1.t.join();// Wait for ob1 to finish
            ob2.t.join(); // Wait for ob2 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        System.out.println("Is ob1 thread alive? " + ob1.t.isAlive());
        System.out.println("Is ob2 thread alive? " + ob2.t.isAlive());  
        System.out.println("Main Thread finished execution.");
    }
}
