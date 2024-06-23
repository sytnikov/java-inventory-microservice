package sytnikov.dev.inventory_microservice.application.supplier;

import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISupplierService {
    public Supplier createSupplier(Supplier supplier);
    public List<Supplier> getAllSuppliers();
    public Optional<Supplier> getSupplierById(UUID supplierId);
    public Supplier updateSupplier(Supplier supplier);
    public void deleteSupplier(UUID supplierId);
}
