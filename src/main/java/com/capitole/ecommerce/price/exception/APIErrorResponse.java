package com.capitole.ecommerce.price.exception;

import java.time.ZonedDateTime;

public class APIErrorResponse {

    private String code;
    private String message;
    private ZonedDateTime date;

    public APIErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.date = ZonedDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getDate() {
        return date;
    }
}
