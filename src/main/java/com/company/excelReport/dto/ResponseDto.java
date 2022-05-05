package com.company.excelReport.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto<T> {
    T value;
    String message;

    public ResponseDto<T> prepareSuccessMessage(T value) {
        return ResponseDto.<T>builder()
                .value(value)
                .message(null)
                .build();
    }

    public ResponseDto<T> prepareFailMessage(String message) {
        return ResponseDto.<T>builder()
                .value(null)
                .message(message)
                .build();
    }
}
