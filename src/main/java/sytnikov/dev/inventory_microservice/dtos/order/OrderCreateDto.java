package sytnikov.dev.inventory_microservice.dtos.order;

import lombok.Data;
import sytnikov.dev.inventory_microservice.domain.order.OrderStatusEnum;

import java.math.BigDecimal;

@Data
public class OrderCreateDto {
    private BigDecimal totalAmount ;
    private OrderStatusEnum status;
}