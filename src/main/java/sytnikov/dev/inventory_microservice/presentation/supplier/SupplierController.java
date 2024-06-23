package sytnikov.dev.inventory_microservice.presentation.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.supplier.ISupplierService;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {

    @Autowired
    private ISupplierService _supplierService;

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        Supplier createdSupplier = _supplierService.createSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = _supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping
    public Optional<Supplier> getSupplierById(UUID supplierId) {
        return _supplierService.getSupplierById(supplierId);
    }

    @PutMapping
    public Supplier updateSupplier(@RequestBody Supplier supplier) {
        return _supplierService.updateSupplier(supplier);
    }

    @DeleteMapping
    public boolean deleteSupplier(UUID supplierId) {
        _supplierService.deleteSupplier(supplierId);
    }
}
