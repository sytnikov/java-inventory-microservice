package sytnikov.dev.inventory_microservice.application.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderReadDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderUpdateDto;
import sytnikov.dev.inventory_microservice.domain.order.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "supplier", target= "supplierReadDto")
    @Mapping(source = "orderItems", target= "orderItemsReadDtos")
    OrderReadDto entityToReadDto(Order order);

    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "orderItemsCreateDtos", target = "orderItems")
    Order createDtoToEntity(OrderCreateDto newOrderDetails);

    void entityFromUpdateDto(OrderUpdateDto updatingOrderDetails, @MappingTarget Order order);
}
