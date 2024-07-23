package sytnikov.dev.inventory_microservice.application.supplier;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierCreateDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierReadDto;
import sytnikov.dev.inventory_microservice.application.supplier.dtos.SupplierUpdateDto;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-23T13:39:42+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public Supplier createDtoToEntity(SupplierCreateDto newSupplierDetails) {
        if ( newSupplierDetails == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setName( newSupplierDetails.getName() );
        supplier.setContactPerson( newSupplierDetails.getContactPerson() );
        supplier.setEmail( newSupplierDetails.getEmail() );

        return supplier;
    }

    @Override
    public SupplierReadDto entityToReadDto(Supplier supplier) {
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

    @Override
    public void entityFromUpdateDto(SupplierUpdateDto updatingSupplierDetails, Supplier supplier) {
        if ( updatingSupplierDetails == null ) {
            return;
        }

        supplier.setName( updatingSupplierDetails.getName() );
        supplier.setContactPerson( updatingSupplierDetails.getContactPerson() );
        supplier.setEmail( updatingSupplierDetails.getEmail() );
    }
}
