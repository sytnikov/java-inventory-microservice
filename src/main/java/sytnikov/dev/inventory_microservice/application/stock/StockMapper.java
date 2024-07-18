package sytnikov.dev.inventory_microservice.application.stock;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockCreateDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockReadDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockUpdateDto;
import sytnikov.dev.inventory_microservice.application.supplier.SupplierMapper;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;


@Mapper(componentModel = "spring", uses = SupplierMapper.class)
public interface StockMapper {
    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    @Mapping(source = "supplierId", target = "supplier.id")
    Stock createDtoToEntity(StockCreateDto newStockDetails);

    @Mapping(source = "supplier", target = "supplierReadDto")
    StockReadDto entityToReadDto(Stock stock);

    @Mapping(source = "supplierId", target = "supplier.id")
    void entityFromUpdateDto(StockUpdateDto updatingStockDetails, @MappingTarget Stock stock);
}