package practical9;


public class ParallelArraySum {

    static class SharedTotal {
        long total = 0;

        void add(long value) {
            total += value;
        }

        synchronized void safeAdd(long value) {
            total += value;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        int size = 1000000;
        int numberOfThreads = 8;

        int[] numbers = new int[size];

        for (int i = 0; i < size; i++) {
            numbers[i] = 1;
        }

        long expected = size;

        Thread[] threads = new Thread[numberOfThreads];

        SharedTotal unsafeTotal = new SharedTotal();

        System.out.println("Expected Sum: " + expected);

        long start = System.nanoTime();

        for (int t = 0; t < numberOfThreads; t++) {

            final int threadNumber = t;

            threads[t] = new Thread(() -> {

                int from = threadNumber * size / numberOfThreads;
                int to = (threadNumber + 1) * size / numberOfThreads;

                for (int i = from; i < to; i++) {
                    unsafeTotal.add(numbers[i]);
                }
            });

            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long unsafeTime = System.nanoTime() - start;

        System.out.println("\nWithout Synchronization:");
        System.out.println("Actual Sum: " + unsafeTotal.total);
        System.out.println("Time: " + unsafeTime / 1000000 + " ms");

        SharedTotal safeTotal = new SharedTotal();

        start = System.nanoTime();

        for (int t = 0; t < numberOfThreads; t++) {

            final int threadNumber = t;

            threads[t] = new Thread(() -> {

                int from = threadNumber * size / numberOfThreads;
                int to = (threadNumber + 1) * size / numberOfThreads;

                for (int i = from; i < to; i++) {
                    safeTotal.safeAdd(numbers[i]);
                }
            });

            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long synchronizedTime = System.nanoTime() - start;

        System.out.println("\nWith Synchronization:");
        System.out.println("Actual Sum: " + safeTotal.total);
        System.out.println("Time: " + synchronizedTime / 1000000 + " ms");

        long[] partialSums = new long[numberOfThreads];

        start = System.nanoTime();

        for (int t = 0; t < numberOfThreads; t++) {

            final int threadNumber = t;

            threads[t] = new Thread(() -> {

                int from = threadNumber * size / numberOfThreads;
                int to = (threadNumber + 1) * size / numberOfThreads;

                long localSum = 0;

                for (int i = from; i < to; i++) {
                    localSum += numbers[i];
                }

                partialSums[threadNumber] = localSum;
            });

            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long finalSum = 0;

        for (long sum : partialSums) {
            finalSum += sum;
        }

        long localTime = System.nanoTime() - start;

        System.out.println("\nUsing Local Partial Sums:");
        System.out.println("Actual Sum: " + finalSum);
        System.out.println("Time: " + localTime / 1000000 + " ms");
    }
}

