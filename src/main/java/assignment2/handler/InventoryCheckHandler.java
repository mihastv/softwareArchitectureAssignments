package assignment2.handler;

import assignment2.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryCheckHandler extends OrderValidationHandler {

    private static final int MAX_ITEMS = 100;

    @Override
    public boolean validate(Order order) {
        log.debug("Checking inventory for order: {}", order.getId());

        if (order.getItemCount() <= 0) {
            log.warn("Order {} failed: No items in order", order.getId());
            return false;
        }

        if (order.getItemCount() > MAX_ITEMS) {
            log.warn("Order {} failed: Exceeds max items ({})", order.getId(), MAX_ITEMS);
            return false;
        }

        if (order.getItemCount() > 50) {
            log.warn("Order {} failed: Insufficient stock", order.getId());
            return false;
        }

        log.info("✓ Inventory check PASSED for order: {}", order.getId());
        return validateNext(order);
    }
}