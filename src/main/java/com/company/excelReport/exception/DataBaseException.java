package com.company.excelReport.exception;

import org.springframework.http.HttpStatus;

public class DataBaseException extends BaseException {
    public DataBaseException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
