package sytnikov.dev.inventory_microservice.presentation.error_handlers;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import sytnikov.dev.inventory_microservice.presentation.loggers.LoggerUtil;
import sytnikov.dev.inventory_microservice.presentation.shared.ErrorEntity;
import sytnikov.dev.inventory_microservice.presentation.shared.ErrorResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger _logger = LoggerUtil.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseEntity> handleValidationException(MethodArgumentNotValidException ex) {

        List<ErrorEntity> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            ErrorEntity error = ErrorEntity
                    .builder()
                    .field(fieldError.getField())
                    .message(fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .build();
            errors.add(error);
        }

        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity();
        errorResponseEntity.setErrors(errors);

        _logger.warn("Validation exception occurred: {}", ex.getMessage());

        return new ResponseEntity<ErrorResponseEntity>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseEntity> handleEntityNotFoundException(EntityNotFoundException ex) {

        ErrorEntity error = ErrorEntity
                .builder()
                .field("Entity not found")
                .message(ex.getMessage())
                .build();

        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity();
        errorResponseEntity.setErrors(Collections.singletonList(error));

        _logger.error("Entity not found exception: {}", ex.getMessage());

        return new ResponseEntity<ErrorResponseEntity>(errorResponseEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseEntity> handleIllegalStateException(IllegalStateException ex) {

        ErrorEntity error = ErrorEntity
                .builder()
                .field("Illegal state change request")
                .message(ex.getMessage())
                .build();

        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity();
        errorResponseEntity.setErrors(Collections.singletonList(error));

        _logger.error("Illegal state exception: {}", ex.getMessage());

        return new ResponseEntity<ErrorResponseEntity>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseEntity> handleAllException(Exception ex) {

        ErrorEntity error = ErrorEntity
                .builder()
                .field("Internal server error")
                .message("Unexpected error: " + ex.getMessage())
                .build();

        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity();
        errorResponseEntity.setErrors(Collections.singletonList(error));

        _logger.error("An unexpected error occurred: {}", ex.getMessage());

        return new ResponseEntity<ErrorResponseEntity>(errorResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
