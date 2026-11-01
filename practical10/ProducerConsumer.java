package practical10;


import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {

    private Queue<Integer> buffer = new LinkedList<>();

    private final int capacity = 3;

    public synchronized void produce(int value)
            throws InterruptedException {

        while (buffer.size() == capacity) {
            wait();
        }

        buffer.add(value);

        System.out.println("Produced: " + value);

        notifyAll();
    }

    public synchronized int consume()
            throws InterruptedException {

        while (buffer.isEmpty()) {
            wait();
        }

        int value = buffer.remove();

        System.out.println("Consumed: " + value);

        notifyAll();

        return value;
    }
}

public class ProducerConsumer {

    public static void main(String[] args) {

        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {

            try {

                for (int i = 1; i <= 10; i++) {

                    buffer.produce(i);

                    Thread.sleep(200);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }, "Producer");

        Thread consumer = new Thread(() -> {

            try {

                for (int i = 1; i <= 10; i++) {

                    buffer.consume();

                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }, "Consumer");

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nAll items produced and consumed.");
    }
}

