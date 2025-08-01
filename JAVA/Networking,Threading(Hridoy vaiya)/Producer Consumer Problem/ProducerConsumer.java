/* 
A BlockingQueue is part of the java.util.concurrent package. 
It extends the Queue interface and adds blocking behavior:
- If a thread tries to insert an element into a full queue, it waits until space becomes available.
- If a thread tries to remove an element from an empty queue, it waits until an element is available.
i.e, thread safe

- Blocking methods:
- put(E e): waits if the queue is full.
- take(): waits if the queue is empty.
- offer(E e, timeout, unit): waits for a limited time to insert.
- poll(timeout, unit): waits for a limited time to retrieve.


magine one thread producing data and another consuming it. A BlockingQueue ensures:
- The producer waits if the queue is full.
- The consumer waits if the queue is empty. This coordination avoids race conditions and busy-waiting.

types of BlockingQueue:
// 1. ArrayBlockingQueue: A bounded blocking queue backed by an array.
// 2. LinkedBlockingQueue: An optionally bounded blocking queue backed by linked nodes.
// 3. PriorityBlockingQueue: An unbounded blocking queue that uses the same ordering rules as java.util.PriorityQueue.
// 4. DelayQueue: A time-based scheduling queue that holds elements until they become available.

*/

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
    BlockingQueue<Integer> queue;
    Producer(BlockingQueue<Integer> queue){
        this.queue = queue;
        new Thread(this, "Producer Thread").start();

    }
    @Override
    public void run() {
        int i = 0;
        while(true){
            if(queue.size() >= 4) {
                System.out.println("Queue is full, waiting to produce...");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                    i++;
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    
                    Thread.sleep(16);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    } 
        
}

class Consumer implements Runnable{
    BlockingQueue<Integer> queue;
    int id;
    Consumer(BlockingQueue<Integer> queue, int id){
        this.queue = queue;
        this.id = id;
        new Thread(this, "Consumer Thread " + id).start();
    }
    @Override
    public void run() {
        while(true){
            if(queue.isEmpty()){
                System.out.println("Queue is empty, waiting...");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                
                    int value = queue.take();
                    System.out.println(id+" Consumed: " + value);
                    Thread.sleep(50);
                
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4); // Bounded queue with capacity 4
        new Producer(queue);
        new Consumer(queue,1);
        new Consumer(queue,2);
        new Consumer(queue,3);
        new Consumer(queue,4);
    }
}
