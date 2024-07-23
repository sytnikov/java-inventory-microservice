package sytnikov.dev.inventory_microservice.presentation;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sytnikov.dev.inventory_microservice.application.supplier.ISupplierService;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierCreateDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierUpdateDto;
import sytnikov.dev.inventory_microservice.presentation.loggers.LoggerUtil;
import sytnikov.dev.inventory_microservice.presentation.shared.SuccessResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {

    private static final Logger _logger = LoggerUtil.getLogger(SupplierController.class);

    @Autowired
    private ISupplierService _supplierService;

    @PostMapping
    public ResponseEntity<SuccessResponseEntity<SupplierReadDto>> addSupplier(@RequestBody @Valid SupplierCreateDto supplierDetails) {
        _logger.debug("Received request to add supplier: {}", supplierDetails);
        SupplierReadDto addedSupplier = _supplierService.addSupplier(supplierDetails);
        SuccessResponseEntity<SupplierReadDto> response = SuccessResponseEntity
                .<SupplierReadDto>builder()
                .data(Collections.singletonList(addedSupplier))
                .build();
        _logger.info("Supplier added with id: {}", addedSupplier.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponseEntity<List<SupplierReadDto>>> getAllSuppliers() {
        _logger.debug("Received request to get all suppliers");
        List<SupplierReadDto> suppliers = _supplierService.getAllSuppliers();
        SuccessResponseEntity<List<SupplierReadDto>> response = SuccessResponseEntity
                .<List<SupplierReadDto>>builder()
                .data(Collections.singletonList(suppliers))
                .build();
        _logger.info("Fetched all suppliers, count: {}", suppliers.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<SupplierReadDto>> getSupplierById(@PathVariable UUID supplierId) {
        _logger.debug("Received request to get supplier by id: {}", supplierId);
        SupplierReadDto foundSupplier = _supplierService.getSupplierById(supplierId);
        SuccessResponseEntity<SupplierReadDto> response = SuccessResponseEntity
                .<SupplierReadDto>builder()
                .data(Collections.singletonList(foundSupplier))
                .build();
        _logger.info("Supplier fetched successfully with id: {}", foundSupplier.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<SupplierReadDto>> modifySupplier(@PathVariable UUID supplierId, @RequestBody @Valid SupplierUpdateDto supplierDetails) {
        _logger.debug("Received request to modify supplier with id: {}", supplierId);
        SupplierReadDto updatedSupplier = _supplierService.modifySupplier(supplierId, supplierDetails);
        SuccessResponseEntity<SupplierReadDto> response = SuccessResponseEntity
                .<SupplierReadDto>builder()
                .data(Collections.singletonList(updatedSupplier))
                .build();
        _logger.info("Supplier modified with id: {}", updatedSupplier.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<String>> deleteSupplier(@PathVariable UUID supplierId) {
        _logger.debug("Received request to delete supplier with id: {}", supplierId);
        _supplierService.deleteSupplier(supplierId);
        SuccessResponseEntity<String> response = SuccessResponseEntity
                .<String>builder()
                .data(Collections.singletonList("Supplier with id " + supplierId + " was successfully deleted"))
                .build();
        _logger.info("Supplier deleted with id: {}", supplierId);
        return ResponseEntity.ok(response);
    }
}
