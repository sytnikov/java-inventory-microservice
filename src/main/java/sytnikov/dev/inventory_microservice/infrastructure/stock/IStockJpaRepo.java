package sytnikov.dev.inventory_microservice.infrastructure.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;

import java.util.UUID;

@Repository
public interface IStockJpaRepo extends JpaRepository<Stock, UUID> {
}
