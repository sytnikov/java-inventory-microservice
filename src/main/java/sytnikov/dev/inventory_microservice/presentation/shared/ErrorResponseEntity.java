package sytnikov.dev.inventory_microservice.presentation.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseEntity {
    public Object data = null;
    public List<ErrorEntity> errors;
}
