//package sytnikov.dev.inventory_microservice.presentation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import sytnikov.dev.inventory_microservice.application.orderItem.IOrderItemService;
//import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/order_items")
//public class OrderItemController {
//
//    @Autowired
//    private IOrderItemService _orderItemService;
//
////    @PostMapping
////    public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItemCreateDTO orderItemDetails) {
////        OrderItem createdOrderItem = _orderItemService.addOrderItem(orderItemDetails);
////        System.out.println("Order item created");
////        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem);
////    }
//
//    @GetMapping
//    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
//        List<OrderItem> orderItems = _orderItemService.getAllOrderItems();
//        return ResponseEntity.ok(orderItems);
//    }
//
//    @GetMapping("/{orderItemId}")
//    public ResponseEntity<Optional<OrderItem>> getOrderItemById(@PathVariable UUID orderItemId) {
//        Optional<OrderItem> foundOrderItem = _orderItemService.getOrderItemById(orderItemId);
//        return ResponseEntity.ok(foundOrderItem);
//    }
//
//    @PutMapping("/{orderItemId}")
//    public ResponseEntity<OrderItem> modifyOrderItem(@PathVariable UUID orderItemId, @RequestBody OrderItem orderItemDetails) {
//        Optional<OrderItem> existingOrderItem = _orderItemService.getOrderItemById(orderItemId);
//
//        if (existingOrderItem.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        orderItemDetails.setId(orderItemId);
//        OrderItem updatedOrderItem = _orderItemService.modifyOrderItem(orderItemDetails);
//        return ResponseEntity.ok(updatedOrderItem);
//    }
//
//    @DeleteMapping("/{orderItemId}")
//    public ResponseEntity<Void> deleteOrderItem(@PathVariable UUID orderItemId) {
//        _orderItemService.deleteOrderItem(orderItemId);
//        System.out.println("Order item deleted");
//        return ResponseEntity.noContent().build();
//    }
//}
