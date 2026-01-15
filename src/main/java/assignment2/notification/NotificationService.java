package assignment2.notification;

import assignment2.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NotificationService {

    private final List<Observer> observers = new ArrayList<>();

    @Autowired
    public NotificationService(List<Observer> autoRegisteredObservers) {
        observers.addAll(autoRegisteredObservers);
        log.info("NotificationService initialized with {} observers", observers.size());
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        log.info("Observer added: {}", observer.getObserverType());
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
        log.info("Observer removed: {}", observer.getObserverType());
    }

    public void notifyObservers(String message, Order order) {
        log.info("Notifying {} observers for order: {}", observers.size(), order.getId());

        for (Observer observer : observers) {
            try {
                observer.update(message, order);
            } catch (Exception e) {
                log.error("Error notifying {}: {}", observer.getObserverType(), e.getMessage());
            }
        }
    }

    public List<String> getObserverTypes() {
        return observers.stream()
                .map(Observer::getObserverType)
                .toList();
    }
}