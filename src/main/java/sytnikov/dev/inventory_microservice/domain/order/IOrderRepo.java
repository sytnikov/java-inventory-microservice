package sytnikov.dev.inventory_microservice.domain.order;

import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderRepo {
    public Order createOne(Order order);
    public List<Order> getAll();
    public Optional<Order> getOneById(UUID orderId);
    public Order updateOne(Order order);
    public void deleteOne(UUID order_id);
}
