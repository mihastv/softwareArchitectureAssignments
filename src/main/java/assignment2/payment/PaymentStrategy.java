package assignment2.payment;

import assignment2.model.Order;

public interface PaymentStrategy {
    boolean pay(Order order);
    String getPaymentMethodName();
}