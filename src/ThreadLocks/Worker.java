package ThreadLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    public synchronized void stage1(){
        synchronized (lock1) {
            Random random = new Random();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list1.add(random.nextInt(100));
        }
    }
    public synchronized void stage2(){
        Random random= new Random();
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list2.add(random.nextInt(100));
        }
    }
    public void process(){
        for(int i=0; i<1000; i++){
                stage1();
                stage2();
        }
    }

    public void main(){
        long start = System.currentTimeMillis();
        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        T2.start();
        T1.start();
        try {
            T1.join();
            T2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time Taken" + (end-start));
        System.out.println("List1: " + list1.size() + " List2: " + list2.size());
    }
}
