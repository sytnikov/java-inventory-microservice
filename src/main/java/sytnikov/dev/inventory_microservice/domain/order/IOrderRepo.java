package sytnikov.dev.inventory_microservice.domain.order;

import java.util.List;
import java.util.UUID;

public interface IOrderRepo {
    public Order createOne(Order order);
    public List<Order> getAll();
    public Order updateOne(Order order);
    public boolean deleteOne(UUID order_id);
}
