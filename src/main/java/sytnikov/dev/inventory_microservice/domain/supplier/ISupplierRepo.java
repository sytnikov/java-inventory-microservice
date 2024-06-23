package sytnikov.dev.inventory_microservice.domain.supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISupplierRepo {
    public Supplier createOne(Supplier supplier);
    public List<Supplier> getAll();
    public Optional<Supplier> getOneById(UUID supplierId);
    public Supplier updateOne(Supplier supplier);
    public void deleteOne(UUID supplierId);
}
