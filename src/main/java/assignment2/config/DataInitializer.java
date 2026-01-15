package assignment2.config;

import assignment2.model.Order;
import assignment2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) {
        log.info("Initializing sample data...");

        Order order1 = Order.builder()
                .customerName("John Doe")
                .customerEmail("john@example.com")
                .customerPhone("+1-555-0101")
                .totalAmount(150.00)
                .paymentMethod("CREDIT_CARD")
                .itemCount(3)
                .status("PENDING")
                .build();

        Order order2 = Order.builder()
                .customerName("Jane Smith")
                .customerEmail("jane@example.com")
                .customerPhone("+1-555-0102")
                .totalAmount(299.99)
                .paymentMethod("PAYPAL")
                .itemCount(5)
                .status("PENDING")
                .build();

        orderRepository.save(order1);
        orderRepository.save(order2);

        log.info("Sample data created: {} orders", orderRepository.count());
    }
}