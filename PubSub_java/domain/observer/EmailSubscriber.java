package domain.observer;

import domain.Message;

public class EmailSubscriber implements SubscriberObserver {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(Message message) {
        System.out.println("Sending EMAIL to " + email + ": New message in topic -> " + message.getContent());

    }
}
