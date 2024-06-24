package sytnikov.dev.inventory_microservice.infrastructure.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sytnikov.dev.inventory_microservice.domain.supplier.ISupplierRepo;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SupplierRepo implements ISupplierRepo {

    @Autowired
    private ISupplierJpaRepo _supplierRepo;

    @Override
    public Supplier createOne(Supplier newSupplier) {
        return _supplierRepo.save(newSupplier);
    }

    @Override
    public List<Supplier> getAll() {
        return _supplierRepo.findAll();
    }

    @Override
    public Optional<Supplier> getOneById(UUID supplierId) {
        return _supplierRepo.findById(supplierId);
    }

    @Override
    public Supplier updateOne(Supplier updatedSupplier) {
        return _supplierRepo.save(updatedSupplier);
    }

    @Override
    public void deleteOne(UUID supplierId) {
        _supplierRepo.deleteById(supplierId);
    }
}
