import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class SMSThread implements Runnable {
    public void run() {
        try {
            Thread.sleep(2000); // 2-second delay for SMS
            System.out.println("SMS Sent using Thread.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EmailThread implements Runnable {
    public void run() {
        try {
            Thread.sleep(3000); // 3-second delay for Email
            System.out.println("Email Sent using Thread.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Creating a subclass of Thread to calculate ETA
class ETACalculator implements Callable<String> {
    private final String location;

    public ETACalculator(String location) {
        this.location = location;
    }

    @Override
    public String call() throws Exception {
        System.out.println("[" + Thread.currentThread().getName() + "] Calculating ETA to:- " + location);
        Thread.sleep(3000);
        return "ETA " + location + " 40 minutes";
    }

}

class Main {
    public static void main(String[] args) {
        Thread smsThread = new Thread(new SMSThread());
        Thread emailThread = new Thread(new EmailThread());
        FutureTask<String> ETAthreadCallable = new FutureTask<>(new ETACalculator("BLR"));
        Thread etaThread = new Thread(ETAthreadCallable);
        System.out.println("Task Started.\n");

        smsThread.start();
        System.out.println("Task 1 ongoing...");

        emailThread.start();
        System.out.println("Task 2 ongoing...");

        etaThread.start();
        System.out.println("Task 3 ongoing...");

        try {
            smsThread.join();
            emailThread.join();
            etaThread.join();
            String eta = ETAthreadCallable.get();
            System.out.println(eta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
