package assignment2.controller;

import assignment2.model.Order;
import assignment2.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        log.info("POST /api/orders - Creating order");
        Order created = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<Order> processOrder(@PathVariable Long id) {
        log.info("POST /api/orders/{}/process", id);
        Order processed = orderService.processOrder(id);
        return ResponseEntity.ok(processed);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) {
        log.info("POST /api/orders/{}/cancel", id);
        Order cancelled = orderService.cancelOrder(id);
        return ResponseEntity.ok(cancelled);
    }

    @PostMapping("/undo")
    public ResponseEntity<Map<String, String>> undo() {
        log.info("POST /api/orders/undo");
        orderService.undoLastAction();
        return ResponseEntity.ok(Map.of("message", "Last action undone"));
    }

    @PostMapping("/redo")
    public ResponseEntity<Map<String, String>> redo() {
        log.info("POST /api/orders/redo");
        orderService.redoLastAction();
        return ResponseEntity.ok(Map.of("message", "Last action redone"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(orderService.getOrdersByStatus(status));
    }
}