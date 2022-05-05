package com.company.excelReport.exception;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseException extends RuntimeException {
    final HttpStatus httpStatus;

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
