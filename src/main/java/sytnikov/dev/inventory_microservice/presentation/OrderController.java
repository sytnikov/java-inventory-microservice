package sytnikov.dev.inventory_microservice.presentation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.order.IOrderService;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderReadDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderUpdateDto;
import sytnikov.dev.inventory_microservice.domain.order.Order;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private IOrderService _orderService;

    @PostMapping
    public ResponseEntity<OrderReadDto> addOrder(@RequestBody @Valid OrderCreateDto orderDetails) {
        OrderReadDto createdOrder = _orderService.addOrder(orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderReadDto>> getAllOrders() {
        List<OrderReadDto> orders = _orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderReadDto> getOrderById(@PathVariable UUID orderId) {
        OrderReadDto foundOrder = _orderService.getOrderById(orderId);
        return ResponseEntity.ok(foundOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderReadDto> modifyOrder(@PathVariable UUID orderId, @RequestBody @Valid OrderUpdateDto orderDetails) {
        OrderReadDto updatedOrder = _orderService.modifyOrder(orderId, orderDetails);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        _orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}





