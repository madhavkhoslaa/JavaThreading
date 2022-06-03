package ProducerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class App {
    private static BlockingQueue<Integer> queue =  new ArrayBlockingQueue<Integer>(20);
    public static void main(String[] args) {
        App r = new App();
        Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                r.Producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                r.Consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });
    t1.start();
    t2.start();
    }
    private void Consumer() throws InterruptedException {
        Random random = new Random();
        while(true){
            Thread.sleep(10);
            int value = this.queue.take();
            if( value % 2 ==0){
                System.out.println("Taken Value: " + value + " Queue Size: " + queue.size());
            }
        }
    }
    private void Producer() throws InterruptedException {
        Random random = new Random();
        while(true){
            try {
                this.queue.put(random.nextInt(100));
            } catch (java.lang.InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/**
 * Producer Consumer problem using blocking queues
 *
 */