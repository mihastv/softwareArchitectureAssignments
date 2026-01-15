package assignment2.command;

import assignment2.model.Order;
import assignment2.notification.NotificationService;
import assignment2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PlaceOrderCommand implements OrderCommand {

    private final Order order;
    private final OrderRepository orderRepository;
    private final NotificationService notificationService;
    private String previousStatus;

    @Override
    public void execute() {
        log.info("Executing PlaceOrderCommand for order: {}", order.getId());

        previousStatus = order.getStatus();
        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);

        notificationService.notifyObservers(
                String.format("Order #%d has been placed! Total: $%.2f",
                        savedOrder.getId(), savedOrder.getTotalAmount()),
                savedOrder
        );

        log.info("Order placed successfully: {}", savedOrder.getId());
    }

    @Override
    public void undo() {
        log.info("Undoing PlaceOrderCommand for order: {}", order.getId());

        order.setStatus(previousStatus != null ? previousStatus : "PENDING");
        orderRepository.save(order);

        notificationService.notifyObservers(
                String.format("Order #%d placement has been reverted", order.getId()),
                order
        );

        log.info("Order placement undone: {}", order.getId());
    }

    @Override
    public String getCommandName() {
        return "PLACE_ORDER";
    }
}