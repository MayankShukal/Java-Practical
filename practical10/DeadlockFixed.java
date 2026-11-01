package practical10;


public class DeadlockFixed {

    static final Object accountA = new Object();
    static final Object accountB = new Object();

    static void transfer(String threadName) {

        synchronized (accountA) {

            System.out.println(threadName + " locked Account A");

            synchronized (accountB) {

                System.out.println(threadName + " locked Account B");

                System.out.println(threadName + " completed transfer");
            }
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            transfer("Thread 1");
        });

        Thread t2 = new Thread(() -> {
            transfer("Thread 2");
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All transfers completed.");
    }
}

