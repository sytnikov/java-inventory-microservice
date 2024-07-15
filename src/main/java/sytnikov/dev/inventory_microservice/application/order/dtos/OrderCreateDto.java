package sytnikov.dev.inventory_microservice.application.order.dtos;

import lombok.Data;
import sytnikov.dev.inventory_microservice.application.orderItem.OrderItemCreateDTO;

import java.util.List;

@Data
public class OrderCreateDto {
    private List<OrderItemCreateDTO> orderItems;
}