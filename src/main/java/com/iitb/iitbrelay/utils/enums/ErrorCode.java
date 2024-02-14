package com.iitb.iitbrelay.utils.enums;

public class ErrorCode {

    public enum OPErrorCode {

        INTERNAL_SERVER(10500, "Internal Server Error"),
        INVALID_REQUEST(10400, "Invalid Request"),
        INVALID_REFERER(10403, "Invalid Referer");

        public final Integer errorCode;
        public final String errorMessage;

        OPErrorCode(Integer errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }


        public String getErrorMessage() {
            return errorMessage;
        }

        public Integer getErrorCode() {
            return errorCode;
        }
    }
}
