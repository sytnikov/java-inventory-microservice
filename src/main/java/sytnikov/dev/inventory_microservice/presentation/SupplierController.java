package sytnikov.dev.inventory_microservice.presentation;

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
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplierDetails) {
        Supplier createdSupplier = _supplierService.addSupplier(supplierDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = _supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Optional<Supplier>> getSupplierById(@PathVariable UUID supplierId) {
        Optional<Supplier> foundSupplier = _supplierService.getSupplierById(supplierId);
        return ResponseEntity.ok(foundSupplier);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Supplier> modifySupplier(@PathVariable UUID supplierId, @RequestBody Supplier supplierDetails) {
        Optional<Supplier> existingSupplier = _supplierService.getSupplierById(supplierId);

        if (existingSupplier.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        supplierDetails.setId(supplierId);
        Supplier updatedSupplier = _supplierService.modifySupplier(supplierDetails);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID supplierId) {
        _supplierService.deleteSupplier(supplierId);
        return ResponseEntity.noContent().build();
    }
}
