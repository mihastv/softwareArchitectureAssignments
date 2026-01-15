package assignment2.notification;

import assignment2.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailNotification implements Observer {

    @Override
    public void update(String message, Order order) {
        log.info("EMAIL NOTIFICATION");
        log.info("To: {}", order.getCustomerEmail());
        log.info("Subject: Order Update - Order #{}", order.getId());
        log.info("Message: {}", message);
    }

    @Override
    public String getObserverType() {
        return "EMAIL";
    }
}