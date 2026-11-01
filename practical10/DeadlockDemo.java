package practical10;


public class DeadlockDemo {

    static final Object accountA = new Object();
    static final Object accountB = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            synchronized (accountA) {

                System.out.println("Thread 1 locked Account A");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println("Thread 1 waiting for Account B");

                synchronized (accountB) {
                    System.out.println("Thread 1 locked Account B");
                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (accountB) {

                System.out.println("Thread 2 locked Account B");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println("Thread 2 waiting for Account A");

                synchronized (accountA) {
                    System.out.println("Thread 2 locked Account A");
                }
            }
        });

        t1.start();
        t2.start();
    }
}

