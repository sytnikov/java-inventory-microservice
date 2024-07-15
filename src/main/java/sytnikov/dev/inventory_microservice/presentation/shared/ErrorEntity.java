package sytnikov.dev.inventory_microservice.presentation.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEntity {
    private String field;
    private String message;
}
