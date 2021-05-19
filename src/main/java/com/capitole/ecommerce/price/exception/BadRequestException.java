package com.capitole.ecommerce.price.exception;

public class BadRequestException extends RuntimeException {
    private String message;
    private String code;

    public BadRequestException(String code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
