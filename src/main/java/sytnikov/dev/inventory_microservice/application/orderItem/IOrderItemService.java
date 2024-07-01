package sytnikov.dev.inventory_microservice.application.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface IOrderItemService {
    public OrderItem addOrderItem(OrderItem orderItem);
    public List<OrderItem> getAllOrderItems();
    public Optional<OrderItem> getOrderItemById(UUID orderItemId);
    public OrderItem modifyOrderItem(OrderItem orderItem);
    public void deleteOrderItem(UUID orderItemId);
}
