package com.iitb.iitbrelay.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadRequestException extends RuntimeException {
    private String errorDescription;
    private Integer errorCode;
    private Throwable exception;

    public BadRequestException(String errorDescription, Integer errorCode) {
        this.errorDescription = errorDescription;
        this.errorCode = errorCode;
    }
}
