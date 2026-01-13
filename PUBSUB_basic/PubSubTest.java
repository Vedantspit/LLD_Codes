import entities.Message;
import subscriber.NewsSubscriber;
import subscriber.Subscriber;

public class PubSubTest {
   public static void main(String[] args) {
      PubSubService pubSub = PubSubService.getInstance();

      String videoTopic = "updatesVideo";
      String newsTopic = "dailyNews";
      pubSub.createTopic(videoTopic);
      pubSub.createTopic(newsTopic);

      Subscriber alice = new NewsSubscriber("Alice");
      Subscriber bob = new NewsSubscriber("Bob");

      pubSub.subscribe(videoTopic, alice);
      pubSub.subscribe(videoTopic, bob);
      pubSub.subscribe(newsTopic, bob);

      System.out.println("\n--- Starting Broadcasts ---");

      Message msg1 = new Message("New Video: Java Concurrency 101");
      pubSub.publish(videoTopic, msg1);

      Message msg2 = new Message("Breaking: AI is everywhere!");
      pubSub.publish(newsTopic, msg2);

      try {
         System.out.println("\nMain thread waiting for deliveries...\n");
         Thread.sleep(2000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      pubSub.shutdown();
   }
}