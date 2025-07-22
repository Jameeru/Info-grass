package Assignment;
public class Threads {

    
    static class Counter {
        int value = 0;

        void increase() {
            value = value + 1;  //Increment the value
        }

        int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {

        Counter counter = new Counter();

        // Create first thread
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increase();
            }
        });

        // Create second thread
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increase();
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // Wait for both to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }

        System.out.println("Final counter value: " + counter.getValue());
    }
}
