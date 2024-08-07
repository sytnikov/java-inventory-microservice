package sytnikov.dev.inventory_microservice.application.order.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sytnikov.dev.inventory_microservice.domain.order.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

@Data
public class OrderCreateDto {
    @NotNull
    private UUID supplierId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @NotNull
    private List<OrderItemCreateDto> orderItemsCreateDtos;
}