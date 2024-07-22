package sytnikov.dev.inventory_microservice.application.order;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemReadDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderReadDto;
import sytnikov.dev.inventory_microservice.application.stock.IStockService;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockReadDto;
import sytnikov.dev.inventory_microservice.application.supplier.ISupplierService;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.domain.order.IOrderRepo;
import sytnikov.dev.inventory_microservice.domain.order.Order;
import sytnikov.dev.inventory_microservice.domain.order.OrderStatusEnum;
import sytnikov.dev.inventory_microservice.domain.orderItem.IOrderItemRepo;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;
import sytnikov.dev.inventory_microservice.domain.stock.IStockRepo;
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
    private IStockRepo _stockRepo;

    @Autowired
    private IStockService _stockService;

    @Autowired
    private ISupplierService _supplierService;

    @Autowired
    private OrderMapper _orderMapper;

    @Autowired
    private OrderItemMapper _orderItemMapper;

    @Override
    @Transactional
    public OrderReadDto addOrder(OrderCreateDto orderDetails) {

        SupplierReadDto orderSupplier = _supplierService.getSupplierById(orderDetails.getSupplierId());

        // creating a map with orderItems to be created: productBarcode and quantity
        Map<String, Integer> orderItemsDetails = new HashMap<>();
        for (OrderItemCreateDto orderItem : orderDetails.getOrderItemsCreateDtos()) {
            String productBarcode = orderItem.getProductBarcode();
            int quantity = orderItem.getQuantity();

            // if there createOrderItemsDtos with the same productBarcodes, the amount is summed up
            // probably it's better not to allow the same productBarcodes in the createOrderItemsDtos
            orderItemsDetails.merge(productBarcode, quantity, Integer::sum);

        }

        // checking if orderItems stock is available and this stock belongs to a particular supplier
        for (Map.Entry<String, Integer> item : orderItemsDetails.entrySet()) {
            try {
                String productBarcode = item.getKey();
                int quantity = item.getValue();

                // here we also check if the product exists in stock
                boolean isInStock = _stockService.isStockAvailable(productBarcode, quantity);
                if (!isInStock) {
                    throw new Exception("Stock for the item " + productBarcode + " is insufficient");
                }

                // check if the product stock belongs to a supplier
                Stock foundStock = _stockRepo.getOneByProductBarcode(productBarcode);
                if (!foundStock.getSupplier().getId().equals(orderSupplier.getId())) {
                    throw new Exception("Product stock (item " + productBarcode + ") doesn't belong to the supplier " + orderSupplier.getId());
                }

                // changing stock quantity
                foundStock.setQuantity(foundStock.getQuantity() - quantity);
                _stockRepo.updateOne(foundStock);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        Order newOrder = _orderMapper.createDtoToEntity(orderDetails);

        // calculating total amount
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
