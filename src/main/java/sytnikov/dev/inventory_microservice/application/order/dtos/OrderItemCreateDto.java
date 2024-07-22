package sytnikov.dev.inventory_microservice.application.order.dtos;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemCreateDto {

    @NotNull
    private String productBarcode;

    @NotNull
    private int quantity;

    @NotNull
    private double unitPrice;
}
