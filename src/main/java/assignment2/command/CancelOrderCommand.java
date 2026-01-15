package assignment2.command;

import assignment2.model.Order;
import assignment2.notification.NotificationService;
import assignment2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CancelOrderCommand implements OrderCommand {

    private final Order order;
    private final OrderRepository orderRepository;
    private final NotificationService notificationService;
    private String previousStatus;

    @Override
    public void execute() {
        log.info("Executing CancelOrderCommand for order: {}", order.getId());

        previousStatus = order.getStatus();

        if ("CANCELLED".equals(previousStatus)) {
            log.warn("Order {} is already cancelled", order.getId());
            return;
        }

        if ("DELIVERED".equals(previousStatus)) {
            log.warn("Cannot cancel order {}: Already delivered", order.getId());
            throw new IllegalStateException("Cannot cancel a delivered order");
        }

        order.setStatus("CANCELLED");
        orderRepository.save(order);

        notificationService.notifyObservers(
                String.format("Order #%d has been cancelled", order.getId()),
                order
        );

        log.info("Order cancelled: {}", order.getId());
    }

    @Override
    public void undo() {
        log.info("Undoing CancelOrderCommand for order: {}", order.getId());

        if (previousStatus != null && !"CANCELLED".equals(previousStatus)) {
            order.setStatus(previousStatus);
            orderRepository.save(order);

            notificationService.notifyObservers(
                    String.format("Order #%d cancellation reverted to: %s",
                            order.getId(), previousStatus),
                    order
            );

            log.info("Order cancellation undone: {}", order.getId());
        }
    }

    @Override
    public String getCommandName() {
        return "CANCEL_ORDER";
    }
}