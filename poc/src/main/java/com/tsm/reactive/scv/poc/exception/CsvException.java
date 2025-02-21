package com.tsm.reactive.scv.poc.exception;

import lombok.Data;

@Data
public class CsvException extends RuntimeException{

    private String msg;

    public CsvException(String msg) {
        this.msg = msg;
    }
}
