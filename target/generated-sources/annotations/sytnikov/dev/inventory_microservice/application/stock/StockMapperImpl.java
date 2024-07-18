package sytnikov.dev.inventory_microservice.application.stock;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockCreateDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockReadDto;
import sytnikov.dev.inventory_microservice.application.stock.dtos.StockUpdateDto;
import sytnikov.dev.inventory_microservice.application.supplier.SupplierMapper;
import sytnikov.dev.inventory_microservice.domain.stock.Stock;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-18T14:04:24+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class StockMapperImpl implements StockMapper {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public Stock createDtoToEntity(StockCreateDto newStockDetails) {
        if ( newStockDetails == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setSupplier( stockCreateDtoToSupplier( newStockDetails ) );
        stock.setProductBarcode( newStockDetails.getProductBarcode() );
        stock.setQuantity( newStockDetails.getQuantity() );

        return stock;
    }

    @Override
    public StockReadDto entityToReadDto(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockReadDto stockReadDto = new StockReadDto();

        stockReadDto.setSupplierReadDto( supplierMapper.entityToReadDto( stock.getSupplier() ) );
        stockReadDto.setId( stock.getId() );
        stockReadDto.setProductBarcode( stock.getProductBarcode() );
        stockReadDto.setQuantity( stock.getQuantity() );
        stockReadDto.setCreatedAt( stock.getCreatedAt() );

        return stockReadDto;
    }

    @Override
    public void entityFromUpdateDto(StockUpdateDto updatingStockDetails, Stock stock) {
        if ( updatingStockDetails == null ) {
            return;
        }

        if ( stock.getSupplier() == null ) {
            stock.setSupplier( new Supplier() );
        }
        stockUpdateDtoToSupplier( updatingStockDetails, stock.getSupplier() );
        stock.setProductBarcode( updatingStockDetails.getProductBarcode() );
        stock.setQuantity( updatingStockDetails.getQuantity() );
    }

    protected Supplier stockCreateDtoToSupplier(StockCreateDto stockCreateDto) {
        if ( stockCreateDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setId( stockCreateDto.getSupplierId() );

        return supplier;
    }

    protected void stockUpdateDtoToSupplier(StockUpdateDto stockUpdateDto, Supplier mappingTarget) {
        if ( stockUpdateDto == null ) {
            return;
        }

        mappingTarget.setId( stockUpdateDto.getSupplierId() );
    }
}
