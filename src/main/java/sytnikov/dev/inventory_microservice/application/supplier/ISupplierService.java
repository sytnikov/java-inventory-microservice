package sytnikov.dev.inventory_microservice.application.supplier;

import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ISupplierService {
    public Supplier addSupplier(Supplier supplier);
    public List<Supplier> getAllSuppliers();
    public Optional<Supplier> getSupplierById(UUID supplierId);
    public Supplier modifySupplier(Supplier supplier);
    public void deleteSupplier(UUID supplierId);
}
