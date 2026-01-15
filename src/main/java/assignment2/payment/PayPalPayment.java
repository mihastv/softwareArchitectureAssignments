package assignment2.payment;

import assignment2.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("PAYPAL")
public class PayPalPayment implements PaymentStrategy {

    @Override
    public boolean pay(Order order) {
        log.info("Processing PayPal payment");
        log.info("Order: #{}", order.getId());
        log.info("Amount: ${}", String.format("%.2f", order.getTotalAmount()));
        log.info("PayPal Account: {}", order.getCustomerEmail());

        boolean success = Math.random() > 0.03;

        if (success) {
            log.info("PayPal payment successful");
        } else {
            log.error("PayPal payment failed");
        }

        return success;
    }

    @Override
    public String getPaymentMethodName() {
        return "PAYPAL";
    }
}