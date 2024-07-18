package sytnikov.dev.inventory_microservice.application.stock.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class StockUpdateDto {

    @Size(min = 12, max = 12, message = "should be 12 digit long")
    private String productBarcode;

    private int quantity;

    private UUID supplierId;
}
