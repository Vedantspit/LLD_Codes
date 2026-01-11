package repository.impl;

import domain.DeliveryStatus;
import domain.MessageDelivery;
import repository.MessageDeliveryRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MessageDeliveryRepositoryImpl implements MessageDeliveryRepository {
    private Map<String, MessageDelivery> deliveries = new ConcurrentHashMap<>();

    @Override
    public void deleteById(String deliveryId) {

    }

    @Override
    public List<MessageDelivery> findPendingBySubscriber(String subscriberId) {
        return deliveries.values().stream()
                .filter(delivery -> delivery.getSubscriberId().equals(subscriberId)
                        && delivery.getStatus() == DeliveryStatus.PENDING)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDelivery save(MessageDelivery message) {
        deliveries.put(message.getId(), message);
        return message;
    }

    @Override
    public void updateDeliveryStatus(String deliveryId, DeliveryStatus status) {

    }

}
