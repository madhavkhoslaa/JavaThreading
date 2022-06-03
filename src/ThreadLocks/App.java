package ThreadLocks;

/**
 * Multiple locks
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        Worker worker1 = new Worker();
        worker1.main();
    }
}

/**
 * Here Thread1 and Thread2 Are running but instead it takes 2x time
 * Why ?
 * Because the processes are synchronised and locks take time
 * However stage1 funtion and stage2 function do not share the same data
 * Hence the locks are useless
 * What we can do is create two locks and synchronise the two locks seperately ?
 * We create two objects lock1 and lock2
 * We wrap the logic for stage1 under lock1 and stage2 under lock2
 */