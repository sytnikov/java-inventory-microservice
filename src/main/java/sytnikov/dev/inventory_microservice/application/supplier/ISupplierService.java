package sytnikov.dev.inventory_microservice.application.supplier;

import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierCreateDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierUpdateDto;

import java.util.List;
import java.util.UUID;

@Service
public interface ISupplierService {
    SupplierReadDto addSupplier(SupplierCreateDto supplierDetails);

    List<SupplierReadDto> getAllSuppliers();

    SupplierReadDto getSupplierById(UUID supplierId);

    SupplierReadDto modifySupplier(UUID supplierId, SupplierUpdateDto supplierDetails);

    void deleteSupplier(UUID supplierId);
}