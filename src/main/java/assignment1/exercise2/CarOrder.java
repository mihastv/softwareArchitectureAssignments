package assignment1.exercise2;

import java.util.UUID;

public class CarOrder {
    private final String orderId;
    private final Car car;
    private final String customerName;
    private final java.time.LocalDateTime orderDate;
    private OrderStatus status;

    public enum OrderStatus {
        PENDING, CONFIRMED, IN_PRODUCTION, SHIPPED, DELIVERED, CANCELLED
    }

    public CarOrder(Car car, String customerName) {
        this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.car = car;
        this.customerName = customerName;
        this.orderDate = java.time.LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    public String getOrderId() { return orderId; }
    public Car getCar() { return car; }
    public String getCustomerName() { return customerName; }
    public java.time.LocalDateTime getOrderDate() { return orderDate; }
    public OrderStatus getStatus() { return status; }

    public void confirmOrder() {
        if (status == OrderStatus.PENDING) {
            status = OrderStatus.CONFIRMED;
        }
    }

    public void cancelOrder() {
        if (status == OrderStatus.PENDING || status == OrderStatus.CONFIRMED) {
            status = OrderStatus.CANCELLED;
        }
    }

    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        String separator = "═".repeat(60);

        sb.append("\n").append(separator).append("\n");
        sb.append("  ORDER CONFIRMATION\n");
        sb.append(separator).append("\n\n");

        sb.append(String.format("  Order ID: %s\n", orderId));
        sb.append(String.format("  Customer: %s\n", customerName));
        sb.append(String.format("  Order Date: %s\n", orderDate.format(
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        sb.append(String.format("  Status: %s\n", status));

        sb.append(car.getFullSpecification());

        return sb.toString();
    }
}