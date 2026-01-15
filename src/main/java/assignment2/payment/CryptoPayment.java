package assignment2.payment;

import assignment2.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("CRYPTO")
public class CryptoPayment implements PaymentStrategy {

    private static final double BTC_RATE = 45000.0;

    @Override
    public boolean pay(Order order) {
        double btcAmount = order.getTotalAmount() / BTC_RATE;

        log.info("Processing Crypto payment");
        log.info("Order: #{}", order.getId());
        log.info("Amount: ${} (≈ {} BTC)",
                String.format("%.2f", order.getTotalAmount()),
                String.format("%.8f", btcAmount));

        if (order.getTotalAmount() < 10) {
            log.error("Crypto payment failed: Minimum $10 required");
            return false;
        }

        boolean success = Math.random() > 0.10;

        if (success) {
            log.info("Crypto payment successful");
        } else {
            log.error("Crypto payment failed");
        }

        return success;
    }

    @Override
    public String getPaymentMethodName() {
        return "CRYPTO";
    }
}