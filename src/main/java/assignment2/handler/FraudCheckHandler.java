package assignment2.handler;

import assignment2.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FraudCheckHandler extends OrderValidationHandler {

    private static final double FRAUD_THRESHOLD = 10000.0;

    @Override
    public boolean validate(Order order) {
        log.debug("Performing fraud check for order: {}", order.getId());

        if (order.getCustomerEmail() != null &&
                order.getCustomerEmail().contains("suspicious")) {
            log.warn("Order {} failed: Suspicious email detected", order.getId());
            return false;
        }

        if (order.getCustomerName() == null || order.getCustomerName().trim().isEmpty()) {
            log.warn("Order {} failed: Missing customer name", order.getId());
            return false;
        }

        if (order.getTotalAmount() > FRAUD_THRESHOLD) {
            log.info("Order {} flagged: High value transaction ({})",
                    order.getId(), order.getTotalAmount());
        }

        log.info("✓ Fraud check PASSED for order: {}", order.getId());
        return validateNext(order);
    }
}