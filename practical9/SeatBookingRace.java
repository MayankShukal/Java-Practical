package practical9;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

class BookingSystem {

    int seatsLeft = 5;
    int successfulBookings = 0;

    CyclicBarrier barrier = new CyclicBarrier(10);

    void unsafeBook() {

        if (seatsLeft > 0) {

            try {
                barrier.await();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            seatsLeft--;
            successfulBookings++;

            System.out.println(
                    Thread.currentThread().getName()
                    + " booked a seat.");
        }
    }

    synchronized void safeBook() {

        if (seatsLeft > 0) {

            seatsLeft--;
            successfulBookings++;

            System.out.println(
                    Thread.currentThread().getName()
                    + " booked a seat.");
        } else {

            System.out.println(
                    Thread.currentThread().getName()
                    + " could not book. No seats left.");
        }
    }
}

public class SeatBookingRace {

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println("Without Synchronization:");

        BookingSystem unsafeSystem = new BookingSystem();

        Thread[] threads = new Thread[10];

        CountDownLatch start = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {

            threads[i] = new Thread(() -> {

                try {
                    start.await();
                    unsafeSystem.unsafeBook();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }, "Thread-" + (i + 1));

            threads[i].start();
        }

        start.countDown();

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Successful Bookings: "
                + unsafeSystem.successfulBookings);

        System.out.println("Seats Left: "
                + unsafeSystem.seatsLeft);

        System.out.println("\nWith Synchronization:");

        BookingSystem safeSystem = new BookingSystem();

        for (int i = 0; i < 10; i++) {

            threads[i] = new Thread(() -> {
                safeSystem.safeBook();
            }, "Thread-" + (i + 1));

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Successful Bookings: "
                + safeSystem.successfulBookings);

        System.out.println("Seats Left: "
                + safeSystem.seatsLeft);
    }
}

