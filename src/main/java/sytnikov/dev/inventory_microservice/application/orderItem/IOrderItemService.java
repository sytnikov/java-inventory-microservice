package sytnikov.dev.inventory_microservice.application.orderItem;

import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.order.Order;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface IOrderItemService {
    public OrderItem addOrderItem(OrderItemCreateDTO orderItemDetails, Order order, Stock stock);
    public List<OrderItem> getAllOrderItems();
    public Optional<OrderItem> getOrderItemById(UUID orderItemId);
    public OrderItem modifyOrderItem(OrderItem orderItem);
    public void deleteOrderItem(UUID orderItemId);
}
