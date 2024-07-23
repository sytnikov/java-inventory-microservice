package sytnikov.dev.inventory_microservice.presentation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.order.IOrderService;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderReadDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderUpdateDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockReadDto;
import sytnikov.dev.inventory_microservice.domain.order.Order;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;
import sytnikov.dev.inventory_microservice.presentation.shared.SuccessResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private IOrderService _orderService;

    @PostMapping
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> addOrder(@RequestBody @Valid OrderCreateDto orderDetails) {
        OrderReadDto addedOrder = _orderService.addOrder(orderDetails);
        SuccessResponseEntity<OrderReadDto> response = SuccessResponseEntity
                .<OrderReadDto>builder()
                .data(Collections.singletonList(addedOrder))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseEntity<List<OrderReadDto>>> getAllOrders() {
        List<OrderReadDto> orders = _orderService.getAllOrders();
        SuccessResponseEntity<List<OrderReadDto>> response = SuccessResponseEntity
                .<List<OrderReadDto>>builder()
                .data(Collections.singletonList(orders))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> getOrderById(@PathVariable UUID orderId) {
        OrderReadDto foundOrder = _orderService.getOrderById(orderId);
        SuccessResponseEntity<OrderReadDto> response = SuccessResponseEntity
                .<OrderReadDto>builder()
                .data(Collections.singletonList(foundOrder))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> modifyOrder(@PathVariable UUID orderId, @RequestBody @Valid OrderUpdateDto orderDetails) {
        OrderReadDto updatedOrder = _orderService.modifyOrder(orderId, orderDetails);
        SuccessResponseEntity<OrderReadDto> response = SuccessResponseEntity
                .<OrderReadDto>builder()
                .data(Collections.singletonList(updatedOrder))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> cancelOrder(@PathVariable UUID orderId) {
        OrderReadDto cancelledOrder = _orderService.cancelOrder(orderId);
        SuccessResponseEntity<OrderReadDto> response = SuccessResponseEntity
                .<OrderReadDto>builder()
                .data(Collections.singletonList(cancelledOrder))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<SuccessResponseEntity<String>> deleteOrder(@PathVariable UUID orderId) {
        _orderService.deleteOrder(orderId);
        SuccessResponseEntity<String> response = SuccessResponseEntity
                .<String>builder()
                .data(Collections.singletonList("Order with id " + orderId + " was successfully deleted"))
                .build();
        return ResponseEntity.ok(response);
    }
}





