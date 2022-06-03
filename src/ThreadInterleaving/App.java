package ThreadInterleaving;

/**
 * Synchronise Keyword java
 * Problems with unsynchronysed threads
 */
public class App {
    private int count = 0;
    public synchronized void inc(){
        count++;
    }
    public static void main(String[] args) {
        App app = new App();
        try {
            app.doWork();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
private void doWork() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< 10000; i++){
                    inc();
                }
            }
        });
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i< 10000; i++){
                inc();
            }
        }
    });
    t1.start();
    t2.start();
    try{
        t1.join();
        t2.join();
    }
    catch (InterruptedException e){
       e.printStackTrace();
    }

    System.out.println("Count is " + count);
}
}

/**
 * This does not output correct values ?
 * Why ?
 * Count++ consists of atomic operations
 * count++ = count = count + 1
 * get Count
 * add 1 to count
 * assign to count
 * Hence 3 operations
 * And there is considerable time taken to do it
 * There are sync problems in loop in threads sometimes hence value is not
 * 20000
 * Some increments are basically being skipped
 *
 *
 * Converting count++
 * to a synchronized function fixes it
 * How ?
 * Every object in java has a intrinsic lock synchronized method
 * basically holds the ability to change it and the next thread waits
 * for the instrinsic lock to free up
 */