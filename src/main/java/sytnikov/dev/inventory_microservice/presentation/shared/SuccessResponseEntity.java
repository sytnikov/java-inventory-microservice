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
public class SuccessResponseEntity<T> {
    public List<T> data;
    public Object errors = null;
}


