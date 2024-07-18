package sytnikov.dev.inventory_microservice.application.order;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemReadDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderReadDto;
import sytnikov.dev.inventory_microservice.application.stock.IStockService;
import sytnikov.dev.inventory_microservice.domain.order.IOrderRepo;
import sytnikov.dev.inventory_microservice.domain.order.Order;
import sytnikov.dev.inventory_microservice.domain.order.OrderStatusEnum;
import sytnikov.dev.inventory_microservice.domain.orderItem.IOrderItemRepo;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepo _orderRepo;

    @Autowired
    private IOrderItemRepo _orderItemRepo;

    @Autowired
    private IStockService _stockService;

    @Autowired
    private OrderMapper _orderMapper;

    @Autowired
    private OrderItemMapper _orderItemMapper;

    @Override
    @Transactional
    public OrderReadDto addOrder(OrderCreateDto orderDetails) {

        Order newOrder = _orderMapper.createDtoToEntity(orderDetails);

        BigDecimal totalAmount = orderDetails
                .getOrderItemsCreateDtos()
                .stream()
                .map(orderItem -> BigDecimal.valueOf(orderItem.getUnitPrice())
                        .multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_EVEN);

        newOrder.setTotalAmount(totalAmount);

        Order addedOrder = _orderRepo.createOne(newOrder);

        List<OrderItem> orderItems = orderDetails.getOrderItemsCreateDtos().stream().map(item -> {
            OrderItem orderItem = _orderItemMapper.createDtoToEntity(item);
            orderItem.setOrder(addedOrder);
            return orderItem;
        }).toList();
        for (OrderItem orderItem : orderItems) {
            _orderItemRepo.createOne(orderItem);
        }

        OrderReadDto orderReadDto = _orderMapper.entityToReadDto(addedOrder);

        List<OrderItemReadDto> orderItemsReadDtos = _orderItemMapper.entitiesToReadDtos(orderItems);

        orderReadDto.setOrderItemsReadDtos(orderItemsReadDtos);

        return orderReadDto;
    }

    @Override
    public List<OrderReadDto> getAllOrders() {
        List<Order> orders = _orderRepo.getAll();
        return orders.stream().map(_orderMapper::entityToReadDto).toList();
    }
//
//    @Override
//    public Optional<Order> getOrderById(UUID orderId) {
//        return _orderRepo.getOneById(orderId);
//    }
//
//    @Override
//    public Order modifyOrder(Order order) {
//        return _orderRepo.updateOne(order);
//    }
//
//    @Override
//    public void deleteOrder(UUID orderId) {
//        _orderRepo.deleteOne(orderId);
//    }
}
