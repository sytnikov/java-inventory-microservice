package sytnikov.dev.inventory_microservice.application.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.orderItem.IOrderItemRepo;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderItemService implements IOrderItemService{

    @Autowired
    private IOrderItemRepo _orderItemRepo;

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        return _orderItemRepo.createOne(orderItem);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return _orderItemRepo.getAll();
    }

    @Override
    public Optional<OrderItem> getOrderItemById(UUID orderItemId) {
        return _orderItemRepo.getOneById(orderItemId);
    }

    @Override
    public OrderItem modifyOrderItem(OrderItem orderItem) {
        return _orderItemRepo.createOne(orderItem);
    }

    @Override
    public void deleteOrderItem(UUID orderItemId) {
        _orderItemRepo.deleteOne(orderItemId);
    }
}
