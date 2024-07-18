package sytnikov.dev.inventory_microservice.application.order.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import sytnikov.dev.inventory_microservice.domain.order.OrderStatusEnum;

@Data
public class OrderUpdateDto {
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
}
