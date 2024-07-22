package sytnikov.dev.inventory_microservice.application.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemCreateDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderItemReadDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderReadDto;
import sytnikov.dev.inventory_microservice.application.order.dtos.OrderUpdateDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.domain.order.Order;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-22T13:53:14+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderReadDto entityToReadDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderReadDto orderReadDto = new OrderReadDto();

        orderReadDto.setSupplierReadDto( supplierToSupplierReadDto( order.getSupplier() ) );
        orderReadDto.setOrderItemsReadDtos( orderItemListToOrderItemReadDtoList( order.getOrderItems() ) );
        orderReadDto.setId( order.getId() );
        orderReadDto.setStatus( order.getStatus() );
        orderReadDto.setTotalAmount( order.getTotalAmount() );
        orderReadDto.setCreatedAt( order.getCreatedAt() );

        return orderReadDto;
    }

    @Override
    public Order createDtoToEntity(OrderCreateDto newOrderDetails) {
        if ( newOrderDetails == null ) {
            return null;
        }

        Order order = new Order();

        order.setSupplier( orderCreateDtoToSupplier( newOrderDetails ) );
        order.setOrderItems( orderItemCreateDtoListToOrderItemList( newOrderDetails.getOrderItemsCreateDtos() ) );
        order.setStatus( newOrderDetails.getStatus() );

        return order;
    }

    @Override
    public void updateEntityFromDto(OrderUpdateDto updatingOrderDetails, Order order) {
        if ( updatingOrderDetails == null ) {
            return;
        }

        order.setStatus( updatingOrderDetails.getStatus() );
    }

    protected SupplierReadDto supplierToSupplierReadDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierReadDto supplierReadDto = new SupplierReadDto();

        supplierReadDto.setId( supplier.getId() );
        supplierReadDto.setName( supplier.getName() );
        supplierReadDto.setContactPerson( supplier.getContactPerson() );
        supplierReadDto.setEmail( supplier.getEmail() );
        supplierReadDto.setCreatedAt( supplier.getCreatedAt() );

        return supplierReadDto;
    }

    protected OrderItemReadDto orderItemToOrderItemReadDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemReadDto orderItemReadDto = new OrderItemReadDto();

        orderItemReadDto.setId( orderItem.getId() );
        orderItemReadDto.setProductBarcode( orderItem.getProductBarcode() );
        orderItemReadDto.setQuantity( orderItem.getQuantity() );
        orderItemReadDto.setUnitPrice( orderItem.getUnitPrice() );
        orderItemReadDto.setCreatedAt( orderItem.getCreatedAt() );

        return orderItemReadDto;
    }

    protected List<OrderItemReadDto> orderItemListToOrderItemReadDtoList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemReadDto> list1 = new ArrayList<OrderItemReadDto>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemToOrderItemReadDto( orderItem ) );
        }

        return list1;
    }

    protected Supplier orderCreateDtoToSupplier(OrderCreateDto orderCreateDto) {
        if ( orderCreateDto == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setId( orderCreateDto.getSupplierId() );

        return supplier;
    }

    protected OrderItem orderItemCreateDtoToOrderItem(OrderItemCreateDto orderItemCreateDto) {
        if ( orderItemCreateDto == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setProductBarcode( orderItemCreateDto.getProductBarcode() );
        orderItem.setQuantity( orderItemCreateDto.getQuantity() );
        orderItem.setUnitPrice( BigDecimal.valueOf( orderItemCreateDto.getUnitPrice() ) );

        return orderItem;
    }

    protected List<OrderItem> orderItemCreateDtoListToOrderItemList(List<OrderItemCreateDto> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemCreateDto orderItemCreateDto : list ) {
            list1.add( orderItemCreateDtoToOrderItem( orderItemCreateDto ) );
        }

        return list1;
    }
}
