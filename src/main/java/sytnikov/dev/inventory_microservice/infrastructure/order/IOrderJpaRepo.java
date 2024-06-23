package sytnikov.dev.inventory_microservice.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sytnikov.dev.inventory_microservice.domain.order.Order;

import java.util.UUID;

@Repository
public interface IOrderJpaRepo extends JpaRepository<Order, UUID> {
}
