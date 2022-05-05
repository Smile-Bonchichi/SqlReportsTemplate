package com.company.excelReport.exception;

import org.springframework.http.HttpStatus;

public class FileCreateException extends BaseException {
    public FileCreateException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
