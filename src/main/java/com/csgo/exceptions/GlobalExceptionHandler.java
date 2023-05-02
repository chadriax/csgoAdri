package com.csgo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.function.EntityResponse;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> entityNotFoundException(EntityNotFoundException exception,
                                                                     WebRequest request){
        ExceptionResponse response = new ExceptionResponse(LocalDate.now(), exception.getMessage());
        return new ResponseEntity<> (response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> paymentException(NotEnoughBalanceException exception){
        ExceptionResponse response = new ExceptionResponse(LocalDate.now(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> notInSaleException(NotInSaleException exception){
        ExceptionResponse response = new ExceptionResponse(LocalDate.now(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
