package sytnikov.dev.inventory_microservice.application.supplier.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SupplierUpdateDto {

    @Size(min = 3, message = "should be longer than 3 characters")
    private String name;

    @Size(min = 3, message = "should be longer than 3 characters")
    private String contactPerson;

    @Email
    private String email;
}
