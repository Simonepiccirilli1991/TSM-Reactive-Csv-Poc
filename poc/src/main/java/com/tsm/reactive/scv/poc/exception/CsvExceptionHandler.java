package com.tsm.reactive.scv.poc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CsvExceptionHandler {


    @ExceptionHandler(CsvException.class)
    public ResponseEntity<CsvException> errorHandler(CsvException e){
        return ResponseEntity.internalServerError().body(e);
    }
}
