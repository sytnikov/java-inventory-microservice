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
        SuccessResponseEntity<SupplierReadDto> response = SuccessResponseEntity
                .<SupplierReadDto>builder()
                .data(Collections.singletonList(addedSupplier))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseEntity<List<SupplierReadDto>>> getAllSuppliers() {
        List<SupplierReadDto> suppliers = _supplierService.getAllSuppliers();
        SuccessResponseEntity<List<SupplierReadDto>> response = SuccessResponseEntity.
                <List<SupplierReadDto>>builder()
                .data(Collections.singletonList(suppliers))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<SupplierReadDto>> getSupplierById(@PathVariable UUID supplierId) {
        SupplierReadDto foundSupplier = _supplierService.getSupplierById(supplierId);
        SuccessResponseEntity<SupplierReadDto> response = SuccessResponseEntity
                .<SupplierReadDto>builder()
                .data(Collections.singletonList(foundSupplier))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<SupplierReadDto>> modifySupplier(@PathVariable UUID supplierId, @RequestBody @Valid SupplierUpdateDto supplierDetails) {
        SupplierReadDto updatedSupplier = _supplierService.modifySupplier(supplierId, supplierDetails);
        SuccessResponseEntity<SupplierReadDto> response = SuccessResponseEntity
                .<SupplierReadDto>builder()
                .data(Collections.singletonList(updatedSupplier))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<String>> deleteSupplier(@PathVariable UUID supplierId) {
        _supplierService.deleteSupplier(supplierId);
        SuccessResponseEntity<String> response = SuccessResponseEntity
                .<String>builder()
                .data(Collections.singletonList("Supplier with id " + supplierId + " was successfully deleted"))
                .build();
        return ResponseEntity.ok(response);
    }
}
