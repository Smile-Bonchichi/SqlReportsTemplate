package com.company.excelReport.exception;

import org.springframework.http.HttpStatus;

public class OutColumnsException extends BaseException {
    public OutColumnsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
