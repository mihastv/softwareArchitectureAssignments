package assignment2.notification;

import assignment2.model.Order;

public interface Observer {
    void update(String message, Order order);
    String getObserverType();
}