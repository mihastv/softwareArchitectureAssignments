package assignment2.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class PaymentContext {

    private final Map<String, PaymentStrategy> strategies = new HashMap<>();

    @Autowired
    public PaymentContext(
            CreditCardPayment creditCard,
            PayPalPayment payPal,
            CryptoPayment crypto) {

        strategies.put("CREDIT_CARD", creditCard);
        strategies.put("PAYPAL", payPal);
        strategies.put("CRYPTO", crypto);

        log.info("PaymentContext initialized with {} strategies", strategies.size());
    }

    public PaymentStrategy getStrategy(String paymentMethod) {
        PaymentStrategy strategy = strategies.get(paymentMethod.toUpperCase());

        if (strategy == null) {
            throw new IllegalArgumentException("Unknown payment method: " + paymentMethod);
        }

        return strategy;
    }
}