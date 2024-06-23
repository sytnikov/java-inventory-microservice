package sytnikov.dev.inventory_microservice.infrastructure.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.UUID;

@Repository
public interface ISupplierJpaRepo extends JpaRepository<Supplier, UUID> {
}
