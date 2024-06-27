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
    private IOrderJpaRepo _orderRepo;


    @Override
    public Order createOne(Order order) {
        return _orderRepo.save(order);
    }

    @Override
    public List<Order> getAll() {
        return _orderRepo.findAll();
    }

    @Override
    public Optional<Order> getOneById(UUID orderId) {
        return _orderRepo.findById(orderId);
    }

    @Override
    public Order updateOne(Order order) {
        return _orderRepo.save(order);
    }

    @Override
    public void deleteOne(UUID order_id) {
        _orderRepo.deleteById(order_id);
    }
}
