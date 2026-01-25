import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// Purchase counter with no protection
class PurchaseCounter {
    // Shared count value
    private volatile int count = 0;

    // Increment the counter
    public void increment() {
        // READ current value
        // INCREMENT it
        // WRITE it back
        count++; // <-- not atomic, unsafe
    }

    // Fetch the current count
    public int getCount() {
        return count;
    }
}// Purchase counter with no protection

class PurchaseAtomicCounter {
    // Shared count value
    private AtomicInteger count = new AtomicInteger(0);

    // Increment the counter
    public void increment() {
        int prev, next;
        do {
            prev = count.get();
            next = prev + 1;
        } while (!count.compareAndSet(prev, next));
    }

    // Fetch the current count
    public int getCount() {
        return count.get();
    }
}

// Demonstrates the race condition
class RaceCond {

    public static void main(String[] args) throws InterruptedException {
        // Create a shared counter
        PurchaseAtomicCounter counter = new PurchaseAtomicCounter();

        // Task that bumps the counter 1000 times
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        // Run the same task in two threads
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Expect 2000, but rarely get it
        System.out.println("Final Count: " + counter.getCount());
    }
}
