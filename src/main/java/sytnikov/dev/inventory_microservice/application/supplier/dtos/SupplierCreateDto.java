package sytnikov.dev.inventory_microservice.application.supplier.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SupplierCreateDto {

    @NotNull
    @Size(min = 3, message = "should be longer than 3 characters")
    private String name;

    @NotNull
    @Size(min = 3, message = "should be longer than 3 characters")
    private String contactPerson;

    @Email
    @NotNull
    private String email;
}
