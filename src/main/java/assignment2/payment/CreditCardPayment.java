package assignment2.payment;

import assignment2.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("CREDIT_CARD")
public class CreditCardPayment implements PaymentStrategy {

    @Override
    public boolean pay(Order order) {
        log.info("Processing Credit Card payment");
        log.info("Order: #{}", order.getId());
        log.info("Amount: ${}", String.format("%.2f", order.getTotalAmount()));

        boolean success = Math.random() > 0.05;

        if (success) {
            log.info("Credit card payment successful");
        } else {
            log.error("Credit card payment failed");
        }

        return success;
    }

    @Override
    public String getPaymentMethodName() {
        return "CREDIT_CARD";
    }
}