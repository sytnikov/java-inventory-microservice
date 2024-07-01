package sytnikov.dev.inventory_microservice.infrastructure.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sytnikov.dev.inventory_microservice.domain.order.IOrderRepo;
import sytnikov.dev.inventory_microservice.domain.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepo implements IOrderRepo {

    @Autowired
    private IOrderJpaRepo _orderJpaRepo;


    @Override
    public Order createOne(Order order) {
        return _orderJpaRepo.save(order);
    }

    @Override
    public List<Order> getAll() {
        return _orderJpaRepo.findAll();
    }

    @Override
    public Optional<Order> getOneById(UUID orderId) {
        return _orderJpaRepo.findById(orderId);
    }

    @Override
    public Order updateOne(Order order) {
        return _orderJpaRepo.save(order);
    }

    @Override
    public void deleteOne(UUID order_id) {
        _orderJpaRepo.deleteById(order_id);
    }
}
