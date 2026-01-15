package assignment1.bonus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

class CarOrder {
    private final String orderId;
    private final Car car;
    private final String customerName;
    private final LocalDateTime orderDate;
    private OrderStatus status;
    private final List<Document> orderDocuments;

    public enum OrderStatus {
        PENDING, CONFIRMED, IN_PRODUCTION, SHIPPED, DELIVERED, CANCELLED
    }

    public CarOrder(Car car, String customerName) {
        this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.car = car;
        this.customerName = customerName;
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.orderDocuments = new ArrayList<>();
    }

    public String getOrderId() { return orderId; }
    public Car getCar() { return car; }
    public String getCustomerName() { return customerName; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public OrderStatus getStatus() { return status; }
    public List<Document> getOrderDocuments() { return Collections.unmodifiableList(orderDocuments); }

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

    public void addDocument(Document document) {
        orderDocuments.add(document);
    }

    public Document generateOrderDocument(String documentType) {
        Document doc = DocumentFactory.createDocument(documentType);

        if (doc instanceof CarDocument) {
            ((CarDocument) doc).setCar(car);
        }

        doc.open();

        StringBuilder content = new StringBuilder();
        content.append("ORDER CONFIRMATION\n");
        content.append("==================\n\n");
        content.append("Order ID: ").append(orderId).append("\n");
        content.append("Customer: ").append(customerName).append("\n");
        content.append("Order Date: ").append(orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        content.append("Status: ").append(status).append("\n\n");

        CarDocumentGenerator generator = new CarDocumentGenerator();
        content.append(generator.generateSpecification(car));

        doc.save(content.toString());
        orderDocuments.add(doc);

        return doc;
    }

    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("ORDER CONFIRMATION\n");

        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Customer: ").append(customerName).append("\n");
        sb.append("Order Date: ").append(orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Documents Generated: ").append(orderDocuments.size()).append("\n\n");

        sb.append("VEHICLE DETAILS:\n");
        sb.append("Model: ").append(car.getYear()).append(" ").append(car.getModel()).append("\n");
        sb.append("VIN: ").append(car.getVin()).append("\n");
        sb.append("Total Price: $").append(String.format("%,.2f", car.getTotalPrice())).append("\n");

        return sb.toString();
    }
}
