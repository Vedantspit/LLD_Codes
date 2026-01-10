package domain;

public class Subscription {
    private String id;
    private String topicId;
    private boolean isActive;
    private String subscriberId;
    private long createdAt;

    public Subscription() {
    }

    public Subscription(String id, String topicId, boolean isActive, String subscriberId, long createdAt) {
        this.id = id;
        this.topicId = topicId;
        this.isActive = isActive;
        this.subscriberId = subscriberId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id='" + id + '\'' +
                ", topicId='" + topicId + '\'' +
                ", subscriberId='" + subscriberId + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
