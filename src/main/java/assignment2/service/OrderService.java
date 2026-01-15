package assignment2.service;

import assignment2.command.*;
import assignment2.handler.*;
import assignment2.model.Order;
import assignment2.notification.NotificationService;
import assignment2.payment.PaymentContext;
import assignment2.payment.PaymentStrategy;
import assignment2.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final NotificationService notificationService;
    private final PaymentContext paymentContext;
    private final CommandInvoker commandInvoker;
    private final OrderValidationHandler validationChain;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            NotificationService notificationService,
            PaymentContext paymentContext,
            CommandInvoker commandInvoker,
            FraudCheckHandler fraudHandler,
            InventoryCheckHandler inventoryHandler,
            PaymentValidationHandler paymentHandler) {

        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
        this.paymentContext = paymentContext;
        this.commandInvoker = commandInvoker;

        fraudHandler.setNext(inventoryHandler);
        inventoryHandler.setNext(paymentHandler);
        this.validationChain = fraudHandler;

        log.info("OrderService initialized with chain: Fraud → Inventory → Payment");
    }

    @Transactional
    public Order createOrder(Order order) {
        log.info("Creating order for customer: {}", order.getCustomerName());

        if (order.getStatus() == null) {
            order.setStatus("PENDING");
        }

        Order saved = orderRepository.save(order);

        notificationService.notifyObservers(
                String.format("New order #%d created", saved.getId()),
                saved
        );

        return saved;
    }

    @Transactional
    public Order processOrder(Long orderId) {
        log.info("Processing order: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        log.info("Starting validation chain...");
        boolean isValid = validationChain.validate(order);

        if (!isValid) {
            order.setStatus("VALIDATION_FAILED");
            orderRepository.save(order);
            notificationService.notifyObservers(
                    String.format("Order #%d failed validation", orderId), order);
            return order;
        }

        log.info("Processing payment...");
        PaymentStrategy paymentStrategy = paymentContext.getStrategy(order.getPaymentMethod());
        boolean paymentSuccess = paymentStrategy.pay(order);

        if (!paymentSuccess) {
            order.setStatus("PAYMENT_FAILED");
            orderRepository.save(order);
            notificationService.notifyObservers(
                    String.format("Order #%d payment failed", orderId), order);
            return order;
        }

        log.info("Placing order...");
        PlaceOrderCommand placeCommand = new PlaceOrderCommand(
                order, orderRepository, notificationService);
        commandInvoker.executeCommand(placeCommand);

        return order;
    }

    @Transactional
    public Order cancelOrder(Long orderId) {
        log.info("Cancelling order: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        CancelOrderCommand cancelCommand = new CancelOrderCommand(
                order, orderRepository, notificationService);
        commandInvoker.executeCommand(cancelCommand);

        return order;
    }

    public void undoLastAction() {
        commandInvoker.undoLastCommand();
    }

    public void redoLastAction() {
        commandInvoker.redoLastCommand();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}