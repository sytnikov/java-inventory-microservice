package sytnikov.dev.inventory_microservice.application.supplier;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierCreateDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierUpdateDto;
import sytnikov.dev.inventory_microservice.domain.supplier.ISupplierRepo;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService implements ISupplierService{

    @Autowired
    private ISupplierRepo _supplierRepo;

    @Autowired
    private SupplierMapper _supplierMapper;

    @Override
    public SupplierReadDto addSupplier(SupplierCreateDto supplierDetails) {
        Supplier supplier = _supplierMapper.createDtoToEntity(supplierDetails);
        Supplier createdSupplier = _supplierRepo.createOne(supplier);
        return _supplierMapper.entityToReadDto(createdSupplier);
    }

    @Override
    public List<SupplierReadDto> getAllSuppliers() {
        List<Supplier> suppliers = _supplierRepo.getAll();
        List<SupplierReadDto> suppliersReadDto = suppliers.stream().map(_supplierMapper::entityToReadDto).toList();
        return suppliersReadDto;
    }

    @Override
    public SupplierReadDto getSupplierById(UUID supplierId) throws EntityNotFoundException {
        Supplier foundSupplier = _supplierRepo.getOneById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + supplierId));
        return _supplierMapper.entityToReadDto(foundSupplier);
    }

    @Override
    public SupplierReadDto modifySupplier(UUID supplierId, SupplierUpdateDto supplierDetails) {
        Supplier foundSupplier = _supplierRepo.getOneById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id " + supplierId));

        _supplierMapper.entityFromUpdateDto(supplierDetails, foundSupplier);

        Supplier updatedSupplier = _supplierRepo.updateOne(foundSupplier);

        return _supplierMapper.entityToReadDto(updatedSupplier);
    }

    @Override
    public void deleteSupplier(UUID supplierId) {
        _supplierRepo.deleteOne(supplierId);
    }
}
