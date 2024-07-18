package sytnikov.dev.inventory_microservice.application.order;

import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderReadDto;
import sytnikov.dev.inventory_microservice.domain.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderService {
    OrderReadDto addOrder(OrderCreateDto orderDetails);
    List<OrderReadDto> getAllOrders();
//    Optional<Order> getOrderById(UUID orderId);
//    Order modifyOrder(Order order);
//    void deleteOrder(UUID orderId);
}
