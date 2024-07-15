package sytnikov.dev.inventory_microservice.application.stock;

import lombok.Data;

import java.util.UUID;

@Data
public class StockCreateDto {
    private UUID supplierId;
    private String productBarcode;
    private int quantity;
}
