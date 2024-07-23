package sytnikov.dev.inventory_microservice.infrastructure.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;

import java.util.List;
import java.util.UUID;

@Repository
public interface IOrderItemJpaRepo extends JpaRepository<OrderItem, UUID> {
    List<OrderItem> findOrderItemsByOrderId(UUID orderId);
}
