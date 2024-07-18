package sytnikov.dev.inventory_microservice.application.order.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderItemReadDto {
    private UUID id;
    private String productBarcode;
    private int quantity;
    private BigDecimal unitPrice;
    private UUID orderId;
    private LocalDateTime createdAt;
}
