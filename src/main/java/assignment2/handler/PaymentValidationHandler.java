package assignment2.handler;

import assignment2.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentValidationHandler extends OrderValidationHandler {

    private static final double MIN_AMOUNT = 1.0;
    private static final double MAX_AMOUNT = 100000.0;

    @Override
    public boolean validate(Order order) {
        log.debug("Validating payment for order: {}", order.getId());

        if (order.getTotalAmount() < MIN_AMOUNT) {
            log.warn("Order {} failed: Amount below minimum", order.getId());
            return false;
        }

        if (order.getTotalAmount() > MAX_AMOUNT) {
            log.warn("Order {} failed: Amount exceeds maximum", order.getId());
            return false;
        }

        if (order.getPaymentMethod() == null || order.getPaymentMethod().isEmpty()) {
            log.warn("Order {} failed: No payment method specified", order.getId());
            return false;
        }

        log.info("✓ Payment validation PASSED for order: {}", order.getId());
        return validateNext(order);
    }
}