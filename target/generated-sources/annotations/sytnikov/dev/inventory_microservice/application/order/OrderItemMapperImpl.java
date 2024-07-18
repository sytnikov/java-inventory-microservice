package sytnikov.dev.inventory_microservice.application.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemReadDto;
import sytnikov.dev.inventory_microservice.domain.order.Order;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-18T22:47:41+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemReadDto entityToReadDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemReadDto orderItemReadDto = new OrderItemReadDto();

        orderItemReadDto.setOrderId( orderItemOrderId( orderItem ) );
        orderItemReadDto.setId( orderItem.getId() );
        orderItemReadDto.setProductBarcode( orderItem.getProductBarcode() );
        orderItemReadDto.setQuantity( orderItem.getQuantity() );
        orderItemReadDto.setUnitPrice( orderItem.getUnitPrice() );
        orderItemReadDto.setCreatedAt( orderItem.getCreatedAt() );

        return orderItemReadDto;
    }

    @Override
    public List<OrderItemReadDto> entitiesToReadDtos(List<OrderItem> orderItems) {
        if ( orderItems == null ) {
            return null;
        }

        List<OrderItemReadDto> list = new ArrayList<OrderItemReadDto>( orderItems.size() );
        for ( OrderItem orderItem : orderItems ) {
            list.add( entityToReadDto( orderItem ) );
        }

        return list;
    }

    @Override
    public OrderItem createDtoToEntity(OrderItemCreateDto orderItemDetails) {
        if ( orderItemDetails == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setProductBarcode( orderItemDetails.getProductBarcode() );
        orderItem.setQuantity( orderItemDetails.getQuantity() );
        orderItem.setUnitPrice( BigDecimal.valueOf( orderItemDetails.getUnitPrice() ) );

        return orderItem;
    }

    @Override
    public List<OrderItem> createDtosToEntities(List<OrderItemCreateDto> orderItemsDetails) {
        if ( orderItemsDetails == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( orderItemsDetails.size() );
        for ( OrderItemCreateDto orderItemCreateDto : orderItemsDetails ) {
            list.add( createDtoToEntity( orderItemCreateDto ) );
        }

        return list;
    }

    private UUID orderItemOrderId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Order order = orderItem.getOrder();
        if ( order == null ) {
            return null;
        }
        UUID id = order.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
