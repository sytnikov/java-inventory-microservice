package sytnikov.dev.inventory_microservice.domain.orderItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderItemRepo {
    OrderItem createOne(OrderItem orderItem);
    List<OrderItem> getAll();
    Optional<OrderItem> getOneById(UUID orderItemId);
    List<OrderItem> getAllByOrderId(UUID orderId);
    OrderItem updateOne(OrderItem orderItem);
    void deleteOne(UUID orderItem_id);
}
