package practical9;


class Counter {

    int count = 0;

    void increment() {
        count++;
    }

    synchronized void safeIncrement() {
        count++;
    }
}

public class CounterRace {

    public static void main(String[] args)
            throws InterruptedException {

        int numberOfThreads = 5;
        int increments = 100000;

        Counter counter = new Counter();

        Thread[] threads = new Thread[numberOfThreads];

        System.out.println("Without Synchronization:");

        for (int i = 0; i < numberOfThreads; i++) {

            threads[i] = new Thread(() -> {

                for (int j = 0; j < increments; j++) {
                    counter.increment();
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Expected Count: "
                + (numberOfThreads * increments));

        System.out.println("Actual Count: " + counter.count);

        Counter safeCounter = new Counter();

        System.out.println("\nWith Synchronization:");

        for (int i = 0; i < numberOfThreads; i++) {

            threads[i] = new Thread(() -> {

                for (int j = 0; j < increments; j++) {
                    safeCounter.safeIncrement();
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Expected Count: "
                + (numberOfThreads * increments));

        System.out.println("Actual Count: " + safeCounter.count);
    }
}

