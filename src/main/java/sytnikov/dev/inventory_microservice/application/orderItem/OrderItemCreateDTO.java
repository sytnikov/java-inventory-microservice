package sytnikov.dev.inventory_microservice.application.orderItem;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemCreateDTO {
    private String productBarcode;
    private int quantity;
    private BigDecimal unitPrice;
}
