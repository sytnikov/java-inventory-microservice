package sytnikov.dev.inventory_microservice.application.supplier;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.supplier.ISupplierRepo;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService implements ISupplierService{

    @Autowired
    private ISupplierRepo _supplierRepo;

    @Override
    public Supplier addSupplier(Supplier supplier) {
        return _supplierRepo.createOne(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return _supplierRepo.getAll();
    }

    @Override
    public Optional<Supplier> getSupplierById(UUID supplierId) throws EntityNotFoundException {
        Supplier foundSupplier = _supplierRepo.getOneById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + supplierId));
        return Optional.ofNullable(foundSupplier);
    }

    @Override
    public Supplier modifySupplier(Supplier supplier) {
        return _supplierRepo.updateOne(supplier);
    }

    @Override
    public void deleteSupplier(UUID supplierId) {
        _supplierRepo.deleteOne(supplierId);
    }
}
