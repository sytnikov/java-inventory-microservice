package sytnikov.dev.inventory_microservice.infrastructure.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sytnikov.dev.inventory_microservice.domain.orderItem.IOrderItemRepo;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderItemRepo implements IOrderItemRepo {

    @Autowired
    private IOrderItemJpaRepo _orderItemJpaRepo;

    @Override
    public OrderItem createOne(OrderItem orderItem) {
        return _orderItemJpaRepo.save(orderItem);
    }

    @Override
    public List<OrderItem> getAll() {
        return _orderItemJpaRepo.findAll();
    }

    @Override
    public Optional<OrderItem> getOneById(UUID orderItemId) {
        return _orderItemJpaRepo.findById(orderItemId);
    }

    @Override
    public OrderItem updateOne(OrderItem orderItem) {
        return _orderItemJpaRepo.save(orderItem);
    }

    @Override
    public void deleteOne(UUID orderItem_id) {
        _orderItemJpaRepo.deleteById(orderItem_id);
    }
}
