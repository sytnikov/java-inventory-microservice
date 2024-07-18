package sytnikov.dev.inventory_microservice.application.stock.dtos;

import lombok.Data;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StockReadDto {
    private UUID id;
    private String productBarcode;
    private int quantity;
    private SupplierReadDto supplierReadDto;
    private LocalDateTime createdAt;

}
