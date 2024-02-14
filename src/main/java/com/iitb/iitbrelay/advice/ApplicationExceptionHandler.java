package com.iitb.iitbrelay.advice;

import com.iitb.iitbrelay.dto.ApplicationResponse;
import com.iitb.iitbrelay.dto.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApplicationResponse<?>> handleBadRequestException(BadRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(handleCustomException(e.getErrorCode(), e.getErrorDescription()));
    }

    @ExceptionHandler(OPServerException.class)
    public ResponseEntity<ApplicationResponse<?>> handleOpServerException(OPServerException e) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(handleCustomException(e.getErrorCode(), e.getErrorDescription()));
    }

    private ApplicationResponse<?> handleCustomException(Integer errorCode, String errorDescription) {
        ApplicationResponse<?> applicationResponse = new ApplicationResponse<>();
        applicationResponse.setErrors(Collections.singletonList(Errors.builder()
                .errorCode(errorCode)
                .message(errorDescription)
                .build()));
        return applicationResponse;
    }
}
