package com.juansenen.gaticket.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice /** Manejador global de excepciones */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /** ExceptionHandler(MethodArgumentNotValidException.class)
     * especifica que este método manejará excepciones de validación de tipo */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {
        // Lógica para manejar excepciones de validación y responder con un código de estado 400
        ErrorMessage errorMessage = new ErrorMessage(400, "Bad Request");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
