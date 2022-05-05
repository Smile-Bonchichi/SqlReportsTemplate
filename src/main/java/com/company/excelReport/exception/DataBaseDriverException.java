package com.company.excelReport.exception;

import org.springframework.http.HttpStatus;

public class DataBaseDriverException extends BaseException {
    public DataBaseDriverException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
