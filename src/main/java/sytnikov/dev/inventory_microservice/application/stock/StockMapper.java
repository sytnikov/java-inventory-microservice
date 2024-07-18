package sytnikov.dev.inventory_microservice.application.stock;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockCreateDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockReadDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockUpdateDto;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;


@Mapper(componentModel = "spring")
public interface StockMapper {

    @Mapping(source = "supplierId", target = "supplier.id")
    Stock createDtoToEntity(StockCreateDto newStockDetails);

    @Mapping(source = "supplier", target = "supplierReadDto")
    StockReadDto entityToReadDto(Stock stock);

    @Mapping(source = "supplierId", target = "supplier.id")
    void entityFromUpdateDto(StockUpdateDto updatingStockDetails, @MappingTarget Stock stock);
}