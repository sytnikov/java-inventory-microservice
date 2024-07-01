package sytnikov.dev.inventory_microservice.infrastructure.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;

import java.util.UUID;

@Repository
public interface IOrderItemJpaRepo extends JpaRepository<OrderItem, UUID> {
}
