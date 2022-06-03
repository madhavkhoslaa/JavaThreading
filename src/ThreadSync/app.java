package ThreadSync;

import java.util.Scanner;

/**
 * Problems with Thread Sync
 * Two Problems:
 * 1. Data Being Cached
 * 2. Threads interleaving
 */

class Processor extends Thread {
    private volatile boolean running = true;
    public  void run(){
        while(running){
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void shutdown(){
        this.running = false;
    }
}
public class app {
    /**
     * In some Java Versions Processor instance
     * and main thread don't have same shared space
     * because of some optimisations(Thread caching)
     * to prevent this happening we keep variables as volatile
     * @param args
     */
    public static void main(String[] args) {
        Processor p1 = new Processor();
        p1.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        p1.shutdown();
    }
}

/**
 * Volatile is used to stop caching variables in a thread from another thread
 */