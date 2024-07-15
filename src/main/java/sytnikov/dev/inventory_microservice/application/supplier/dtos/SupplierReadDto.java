package sytnikov.dev.inventory_microservice.application.supplier.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SupplierReadDto {
    private UUID id;
    private String name;
    private String contactPerson;
    private String email;
    private LocalDateTime createdAt;
}
