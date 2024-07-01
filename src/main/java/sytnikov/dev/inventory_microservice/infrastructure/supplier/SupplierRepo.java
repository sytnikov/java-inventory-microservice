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
    private ISupplierJpaRepo _supplierJpaRepo;

    @Override
    public Supplier createOne(Supplier newSupplier) {
        return _supplierJpaRepo.save(newSupplier);
    }

    @Override
    public List<Supplier> getAll() {
        return _supplierJpaRepo.findAll();
    }

    @Override
    public Optional<Supplier> getOneById(UUID supplierId) {
        return _supplierJpaRepo.findById(supplierId);
    }

    @Override
    public Supplier updateOne(Supplier updatedSupplier) {
        return _supplierJpaRepo.save(updatedSupplier);
    }

    @Override
    public void deleteOne(UUID supplierId) {
        _supplierJpaRepo.deleteById(supplierId);
    }
}
