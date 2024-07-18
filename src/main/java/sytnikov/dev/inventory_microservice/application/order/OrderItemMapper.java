package sytnikov.dev.inventory_microservice.application.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemReadDto;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "order.id", target = "orderId")
    OrderItemReadDto entityToReadDto(OrderItem orderItem);

    List<OrderItemReadDto> entitiesToReadDtos(List<OrderItem> orderItems);

    OrderItem createDtoToEntity(OrderItemCreateDto orderItemDetails);

    List<OrderItem> createDtosToEntities(List<OrderItemCreateDto> orderItemsDetails);
}
