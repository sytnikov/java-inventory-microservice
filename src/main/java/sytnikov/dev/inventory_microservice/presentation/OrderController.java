package sytnikov.dev.inventory_microservice.presentation;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.order.IOrderService;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderReadDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderUpdateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;
import sytnikov.dev.inventory_microservice.presentation.loggers.LoggerUtil;
import sytnikov.dev.inventory_microservice.presentation.shared.SuccessResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private static final Logger _logger = LoggerUtil.getLogger(OrderController.class);

    @Autowired
    private IOrderService _orderService;

    @PostMapping
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> addOrder(@RequestBody @Valid OrderCreateDto orderDetails) {
        _logger.debug("Received request to add order: {}", orderDetails);
        OrderReadDto addedOrder = _orderService.addOrder(orderDetails);
        SuccessResponseEntity<OrderReadDto> response = SuccessResponseEntity
                .<OrderReadDto>builder()
                .data(Collections.singletonList(addedOrder))
                .build();
        _logger.info("Order added with id: {}", addedOrder.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseEntity<List<OrderReadDto>>> getAllOrders() {
        _logger.debug("Received request to get all orders");
        List<OrderReadDto> orders = _orderService.getAllOrders();
        SuccessResponseEntity<List<OrderReadDto>> response = SuccessResponseEntity
                .<List<OrderReadDto>>builder()
                .data(Collections.singletonList(orders))
                .build();
        _logger.info("Fetched all orders, count: {}", orders.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> getOrderById(@PathVariable UUID orderId) {
        _logger.debug("Received request to get order by id: {}", orderId);
        OrderReadDto foundOrder = _orderService.getOrderById(orderId);
        SuccessResponseEntity<OrderReadDto> response = SuccessResponseEntity
                .<OrderReadDto>builder()
                .data(Collections.singletonList(foundOrder))
                .build();
        _logger.info("Order fetched successfully with id: {}", foundOrder.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> modifyOrder(@PathVariable UUID orderId, @RequestBody @Valid OrderUpdateDto orderDetails) {
        _logger.debug("Received request to modify order with id: {}", orderId);
        OrderReadDto updatedOrder = _orderService.modifyOrder(orderId, orderDetails);
        SuccessResponseEntity<OrderReadDto> response = SuccessResponseEntity
                .<OrderReadDto>builder()
                .data(Collections.singletonList(updatedOrder))
                .build();
        _logger.info("Order modified with id: {}", updatedOrder.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<SuccessResponseEntity<OrderReadDto>> cancelOrder(@PathVariable UUID orderId) {
        _logger.debug("Received request to cancel order with id: {}", orderId);
        OrderReadDto cancelledOrder = _orderService.cancelOrder(orderId);
        SuccessResponseEntity<OrderReadDto> response = SuccessResponseEntity
                .<OrderReadDto>builder()
                .data(Collections.singletonList(cancelledOrder))
                .build();
        _logger.info("Order cancelled with id: {}", cancelledOrder.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<SuccessResponseEntity<String>> deleteOrder(@PathVariable UUID orderId) {
        _logger.debug("Received request to delete order with id: {}", orderId);
        _orderService.deleteOrder(orderId);
        SuccessResponseEntity<String> response = SuccessResponseEntity
                .<String>builder()
                .data(Collections.singletonList("Order with id " + orderId + " was successfully deleted"))
                .build();
        _logger.info("Order deleted with id: {}", orderId);
        return ResponseEntity.ok(response);
    }
}





