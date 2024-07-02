package sytnikov.dev.inventory_microservice.dtos.stock;

import lombok.Data;

import java.util.UUID;

@Data
public class StockCreateDto {
    private UUID supplierId;
    private UUID productId;
    private String productBarcode;
    private int quantity;
}
