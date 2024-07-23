package sytnikov.dev.inventory_microservice.domain.supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISupplierRepo {
    Supplier createOne(Supplier supplier);
    List<Supplier> getAll();
    Optional<Supplier> getOneById(UUID supplierId);
    Supplier updateOne(Supplier supplier);
    void deleteOne(UUID supplierId);
}
