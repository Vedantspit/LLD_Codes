
// Implementing Runnable Interface
import java.io.*;

// Class inheriting Runnable interface
class RunnableClass implements Runnable {
    // Override run method
    @Override
    public void run() {
        System.out.println("Inside run method");
    }
}

class Geeks {
    public static void main(String args[]) {
        // Create an object of Runnable target
        RunnableClass gfg = new RunnableClass();

        // Pass the runnable reference to Thread
        Thread t = new Thread(gfg, "gfg");

        // Start the thread
        t.start();

        // Get the name of the thread
        System.out.println(t.getName());

        System.out.println(t.currentThread().getName());
    }
}