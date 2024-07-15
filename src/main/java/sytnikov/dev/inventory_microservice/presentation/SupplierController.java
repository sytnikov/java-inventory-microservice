package sytnikov.dev.inventory_microservice.presentation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.supplier.ISupplierService;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierCreateDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierUpdateDto;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;
import sytnikov.dev.inventory_microservice.presentation.shared.SuccessResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {

    @Autowired
    private ISupplierService _supplierService;

    @PostMapping
    public ResponseEntity<SuccessResponseEntity<SupplierReadDto>> addSupplier(@RequestBody @Valid SupplierCreateDto supplierDetails) {
        SupplierReadDto addedSupplier = _supplierService.addSupplier(supplierDetails);

        // Create a SuccessResponseEntity object
        SuccessResponseEntity<SupplierReadDto> response = SuccessResponseEntity.<SupplierReadDto>builder()
                .data(Collections.singletonList(addedSupplier))
                .errors(null) // No errors
                .build();

        // Return the response entity with the SuccessResponseEntity
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SupplierReadDto>> getAllSuppliers() {
        List<SupplierReadDto> suppliers = _supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierReadDto> getSupplierById(@PathVariable UUID supplierId) {
        SupplierReadDto foundSupplier = _supplierService.getSupplierById(supplierId);
        return ResponseEntity.ok(foundSupplier);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<SupplierReadDto> modifySupplier(@PathVariable UUID supplierId, @RequestBody SupplierUpdateDto supplierDetails) {
        SupplierReadDto updatedSupplier = _supplierService.modifySupplier(supplierId, supplierDetails);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID supplierId) {
        _supplierService.deleteSupplier(supplierId);
        return ResponseEntity.noContent().build();
    }
}
