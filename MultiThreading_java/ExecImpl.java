import java.util.concurrent.*;

public class ExecImpl {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void sendEmail(String recipient) {
        executor.execute(() -> {
            System.out.println("Sending email to " + recipient + " by " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Email sent to " + recipient);
        });
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 25; i++) {
            sendEmail("user" + i + "@gmail.com");
        }
        executor.shutdown();
    }

}
