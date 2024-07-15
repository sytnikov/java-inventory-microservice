//package sytnikov.dev.inventory_microservice.application.orderItem;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import sytnikov.dev.inventory_microservice.application.stock.IStockService;
//import sytnikov.dev.inventory_microservice.domain.order.Order;
//import sytnikov.dev.inventory_microservice.domain.orderItem.IOrderItemRepo;
//import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;
//import sytnikov.dev.inventory_microservice.domain.stock.Stock;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class OrderItemService implements IOrderItemService{
//
//    @Autowired
//    private IOrderItemRepo _orderItemRepo;
//
//    @Autowired
//    private IStockService _stockService;
//
//    @Override
//    public OrderItem addOrderItem(OrderItemCreateDTO orderItemDetails, Order order, Stock stock) {
//        OrderItem newOrderItem = new OrderItem();
//
//        String orderItemBarcode = orderItemDetails.getProductBarcode();
//        int orderItemQuantity = orderItemDetails.getQuantity();
//        BigDecimal orderItemPrice = orderItemDetails.getUnitPrice();
//
//        boolean availableStock = _stockService.isStockAvailable(orderItemBarcode, orderItemQuantity);
//
//        if (!availableStock) {
//            throw new RuntimeException("The amount of product with the barcode " + orderItemBarcode + " is not sufficient to proceed with the order");
//        }
//
//        newOrderItem.setId(UUID.randomUUID());
//        newOrderItem.setQuantity(orderItemQuantity);
//        newOrderItem.setUnitPrice(orderItemPrice);
//        newOrderItem.setOrder(order);
//
//        return _orderItemRepo.createOne(newOrderItem);
//    }
//
//    @Override
//    public List<OrderItem> getAllOrderItems() {
//        return _orderItemRepo.getAll();
//    }
//
//    @Override
//    public Optional<OrderItem> getOrderItemById(UUID orderItemId) {
//        return _orderItemRepo.getOneById(orderItemId);
//    }
//
//    @Override
//    public OrderItem modifyOrderItem(OrderItem orderItem) {
//        return _orderItemRepo.createOne(orderItem);
//    }
//
//    @Override
//    public void deleteOrderItem(UUID orderItemId) {
//        _orderItemRepo.deleteOne(orderItemId);
//    }
//}
