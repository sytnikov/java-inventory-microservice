package sytnikov.dev.inventory_microservice.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.order.IOrderService;
import sytnikov.dev.inventory_microservice.domain.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private IOrderService _orderService;

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order orderDetails) {
        Order createdOrder = _orderService.addOrder(orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = _orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable UUID orderId) {
        Optional<Order> foundOrder = _orderService.getOrderById(orderId);
        return ResponseEntity.ok(foundOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> modifyOrder(@PathVariable UUID orderId, @RequestBody Order orderDetails) {
        Optional<Order> existingOrder = _orderService.getOrderById(orderId);

        if (existingOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        orderDetails.setId(orderId);
        Order updatedOrder = _orderService.modifyOrder(orderDetails);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        _orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}





