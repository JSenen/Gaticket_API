package com.juansenen.gaticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFound ex) {
        ErrorMessage errorMessage = new ErrorMessage(404, ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
