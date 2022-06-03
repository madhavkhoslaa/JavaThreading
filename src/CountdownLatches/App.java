package CountdownLatches;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Worker implements Runnable {
        CountDownLatch latch;
        int Div;
        private List<Integer> list1 = new ArrayList<Integer>();
    Worker(CountDownLatch latch, int DIV){
        this.latch = latch;
        this.Div = DIV;
    }
    public void run(){
        try {
            Thread.sleep(10);
            System.out.println("Run" + this.Div);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.latch.countDown();
    }
}
public class App {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i=0; i<100; i++){
            executor.submit(new Worker(latch,i));
        }
        executor.shutdown();
        executor.awaitTermination(20, TimeUnit.DAYS);
        System.out.println("Completed");
    }

}


/**
 * Countdown Latch is a thread safe class
 * It makes one thread wait until a process is done
 */