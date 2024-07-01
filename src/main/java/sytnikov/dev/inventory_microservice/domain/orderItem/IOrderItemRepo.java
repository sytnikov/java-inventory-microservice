package sytnikov.dev.inventory_microservice.domain.orderItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderItemRepo {
    public OrderItem createOne(OrderItem orderItem);
    public List<OrderItem> getAll();
    public Optional<OrderItem> getOneById(UUID orderItemId);
    public OrderItem updateOne(OrderItem orderItem);
    public void deleteOne(UUID orderItem_id);
}
