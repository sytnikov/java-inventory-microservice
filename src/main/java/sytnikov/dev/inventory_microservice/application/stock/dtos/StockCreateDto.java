package sytnikov.dev.inventory_microservice.application.stock.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class StockCreateDto {

    @NotNull
    @Size(min = 12, max = 12, message = "should be 12 digit long")
    private String productBarcode;

    @NotNull
    @Min(value = 0)
    private int quantity;

    @NotNull
    private UUID supplierId;
}
