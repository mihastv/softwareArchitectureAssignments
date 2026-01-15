package assignment2.notification;

import assignment2.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SMSNotification implements Observer {

    @Override
    public void update(String message, Order order) {
        if (order.getCustomerPhone() == null || order.getCustomerPhone().isEmpty()) {
            log.debug("SMS skipped: No phone number for order {}", order.getId());
            return;
        }

        log.info("SMS NOTIFICATION");
        log.info("To: {}", order.getCustomerPhone());
        log.info("Message: {}", message);
    }

    @Override
    public String getObserverType() {
        return "SMS";
    }
}