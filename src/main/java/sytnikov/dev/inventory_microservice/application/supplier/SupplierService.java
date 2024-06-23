package sytnikov.dev.inventory_microservice.application.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import sytnikov.dev.inventory_microservice.domain.supplier.ISupplierRepo;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SupplierService implements ISupplierService{

    @Autowired
    private ISupplierRepo _supplierRepo;

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return _supplierRepo.createOne(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return _supplierRepo.getAll();
    }

    @Override
    public Optional<Supplier> getSupplierById(UUID supplierId) {
        return _supplierRepo.getOneById(supplierId);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return _supplierRepo.updateOne(supplier);
    }

    @Override
    public void deleteSupplier(UUID supplierId) {
        _supplierRepo.deleteOne(supplierId);
    }
}
