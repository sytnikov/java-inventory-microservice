//package sytnikov.dev.inventory_microservice.application.order;
//
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;
//import sytnikov.dev.inventory_microservice.application.orderItem.IOrderItemService;
//import sytnikov.dev.inventory_microservice.application.stock.IStockService;
//import sytnikov.dev.inventory_microservice.domain.order.IOrderRepo;
//import sytnikov.dev.inventory_microservice.domain.order.Order;
//import sytnikov.dev.inventory_microservice.domain.order.OrderStatusEnum;
//import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;
//import sytnikov.dev.inventory_microservice.domain.stock.Stock;
//import sytnikov.dev.inventory_microservice.application.orderItem.OrderItemCreateDTO;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//@Service
//public class OrderService implements IOrderService {
//
//    @Autowired
//    private IOrderRepo _orderRepo;
//
//    @Autowired
//    private IOrderItemService _orderItemService;
//
//    @Autowired
//    private IStockService _stockService;
//
//    @Override
//    @Transactional
//    public Order addOrder(OrderCreateDto orderDetails) {
//
//        if (orderDetails == null || orderDetails.getOrderItems().isEmpty()) {
//            throw new IllegalArgumentException("There are no order items to be added.");
//        }
//        Order newOrder = new Order();
//        List<OrderItem> orderItems = new ArrayList<>();
//        BigDecimal totalAmount = BigDecimal.valueOf(0);
//
//        newOrder.setId(UUID.randomUUID());
//        newOrder.setTotalAmount(totalAmount);
//        newOrder.setStatus(OrderStatusEnum.PENDING);
//
//        Order savedOrder = _orderRepo.createOne(newOrder);
//        for (OrderItemCreateDTO orderItem : orderDetails.getOrderItems()) {
//            String productBarcode = orderItem.getProductBarcode();
//            Stock orderItemStock = _stockService.getStockByProductBarcode(productBarcode);
//            totalAmount = totalAmount.add(orderItem.getUnitPrice());
//            OrderItem newOrderItem = _orderItemService.addOrderItem(orderItem, savedOrder, orderItemStock);
//            orderItems.add(newOrderItem);
//        }
//
//        savedOrder.setTotalAmount(totalAmount);
//        savedOrder.setOrderItems(orderItems);
//
//        return _orderRepo.createOne(savedOrder);
//    }
//
//    @Override
//    public List<Order> getAllOrders() {
//        return _orderRepo.getAll();
//    }
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
//}
