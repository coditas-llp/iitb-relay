package com.iitb.iitbrelay.advice;

import com.iitb.iitbrelay.utils.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OPServerException extends RuntimeException {
    private String errorDescription;
    private Integer errorCode;
    private Throwable exception;

    public OPServerException(String errorDescription, Integer errorCode) {
        this.errorDescription = errorDescription;
        this.errorCode = errorCode;
    }

    public OPServerException(ErrorCode.OPErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorDescription = errorCode.getErrorMessage();
    }
}
