package sytnikov.dev.inventory_microservice.application.supplier;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierCreateDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierUpdateDto;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier createDtoToEntity(SupplierCreateDto newSupplierDetails);
    SupplierReadDto entityToReadDto(Supplier supplier);
    void entityFromUpdateDto(SupplierUpdateDto updatingSupplierDetails, @MappingTarget Supplier supplier);
}
