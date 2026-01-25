import java.util.concurrent.*;

class EmailStatus {
    public String status = "PENDING";
}

public class RunnableEx {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        EmailStatus emailStatus = new EmailStatus();
        Future<EmailStatus> future = executor.submit(() -> {
            try {
                Thread.sleep(2000);
                emailStatus.status = "COMPLETE";
                System.out.println("Runnable task updated status.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, emailStatus);

        try {
            System.out.println("BEFORE GET " + future.isDone());
            EmailStatus res = future.get();
            System.out.println("AFTER GET " + future.isDone());
            System.out.println("Final Status from Future: " + res.status);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
