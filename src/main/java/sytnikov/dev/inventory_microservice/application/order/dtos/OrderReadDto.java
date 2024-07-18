package sytnikov.dev.inventory_microservice.application.order.dtos;

import jakarta.persistence.*;
import lombok.Data;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.domain.order.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderReadDto {
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    private BigDecimal totalAmount;
    private SupplierReadDto supplierReadDto;
    private List<OrderItemReadDto> orderItemsReadDtos;
    private LocalDateTime createdAt;

}
