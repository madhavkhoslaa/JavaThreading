package ThreadPool;

import java.util.SortedMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
    private int id;
    Processor(int id){
        this.id = id;
    }
    public void run(){
        System.out.println("Starting" + this.id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Completed: " + id);
    }
}
public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i= 0; i< 5; i++){
            executor.submit(new Processor(i));
        }
        System.out.println("All tasks completed");
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

/**
 * Executor Service creates a thread pool, it is like workers
 * Each worker gets assigned one task from funtion executor.submit()
 * One iteration Thread1 does the task
 * Next iteration thread2 does the task
 */