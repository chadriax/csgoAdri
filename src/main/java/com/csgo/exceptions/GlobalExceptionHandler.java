package com.csgo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.function.EntityResponse;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;

@Slf4j
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

    @ExceptionHandler(HttpRequestFailedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpRequestFailedException(HttpRequestFailedException ex) {

        String errorMsg = "Couldn't connect with the microservice. ";

        ExceptionResponse res = new ExceptionResponse(LocalDate.now(), errorMsg, Arrays.asList(ex.getMessage()));

        log.error(errorMsg + ex.getMessage());

        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
