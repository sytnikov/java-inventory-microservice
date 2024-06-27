package sytnikov.dev.inventory_microservice.application.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.order.IOrderRepo;
import sytnikov.dev.inventory_microservice.domain.order.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepo _orderRepo;

    @Override
    public Order addOrder(Order order) {
        return _orderRepo.createOne(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return _orderRepo.getAll();
    }

    @Override
    public Optional<Order> getOrderById(UUID orderId) {
        return _orderRepo.getOneById(orderId);
    }

    @Override
    public Order modifyOrder(Order order) {
        return _orderRepo.updateOne(order);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        _orderRepo.deleteOne(orderId);
    }
}
