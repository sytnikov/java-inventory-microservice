package sytnikov.dev.inventory_microservice.application.order;

import sytnikov.dev.inventory_microservice.domain.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderService {
    public Order addOrder(Order order);
    public List<Order> getAllOrders();
    public Optional<Order> getOrderById(UUID orderId);
    public Order modifyOrder(Order order);
    public void deleteOrder(UUID orderId);
}
