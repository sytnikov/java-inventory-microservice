package sytnikov.dev.inventory_microservice.application.supplier.dtos;

import lombok.Data;

@Data
public class SupplierCreateDto {
    private String name;
    private String contactPerson;
    private String email;
}
