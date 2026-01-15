package assignment2.handler;

import assignment2.model.Order;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public abstract class OrderValidationHandler {

    protected OrderValidationHandler nextHandler;

    public void setNext(OrderValidationHandler next) {
        this.nextHandler = next;
    }

    public abstract boolean validate(Order order);

    protected boolean validateNext(Order order) {
        if (nextHandler != null) {
            return nextHandler.validate(order);
        }
        log.info("All validations passed for order {}", order.getId());
        return true;
    }
}