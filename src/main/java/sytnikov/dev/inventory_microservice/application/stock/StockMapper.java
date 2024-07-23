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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "supplier.id", source = "supplierId")
    Stock createDtoToEntity(StockCreateDto newStockDetails);

    @Mapping(source = "supplier", target = "supplierReadDto")
    StockReadDto entityToReadDto(Stock stock);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "supplier.id", source = "supplierId")
    void updateEntityFromDto(StockUpdateDto updatingStockDetails, @MappingTarget Stock stock);
}