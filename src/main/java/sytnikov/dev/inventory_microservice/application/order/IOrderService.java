package sytnikov.dev.inventory_microservice.application.order;

import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderReadDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderUpdateDto;


import java.util.List;
import java.util.UUID;

public interface IOrderService {
    OrderReadDto addOrder(OrderCreateDto orderDetails);
    List<OrderReadDto> getAllOrders();
    OrderReadDto getOrderById(UUID orderId);
    OrderReadDto modifyOrder(UUID orderId, OrderUpdateDto orderDetails);
    OrderReadDto cancelOrder(UUID orderId);
    void deleteOrder(UUID orderId);
}
