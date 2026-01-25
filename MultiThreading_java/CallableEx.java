import java.util.concurrent.*;

public class CallableEx {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 1. Define the Callable (returns a String)
        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Report Generated at " + System.currentTimeMillis();
        };

        System.out.println("Submitting Callable task...");
        Future<String> future = executor.submit(task);

        try {
            // 2. Do other work here while the task runs...
            System.out.println("Doing other work in main thread...");

            // 3. Get the result (this blocks until the task is done)
            String result = future.get();
            System.out.println("Result from Callable: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}