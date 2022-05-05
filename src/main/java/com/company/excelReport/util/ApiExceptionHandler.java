package com.company.excelReport.util;

import com.company.excelReport.dto.ResponseDto;
import com.company.excelReport.exception.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {
            OutColumnsException.class,
            DataBaseDriverException.class,
            DataBaseException.class,
            FileCreateException.class
    })
    public ResponseEntity<ResponseDto<String>> handleFailException(BaseException baseException) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setMessage(baseException.getMessage());

        return new ResponseEntity<>(responseDto, baseException.getHttpStatus());
    }
}
