package com.capitole.ecommerce.price.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleNotFoundExceptions(
            NotFoundException ex, WebRequest request) {

        APIErrorResponse notFound = new APIErrorResponse(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIErrorResponse> handleBadRequestExceptions(
            BadRequestException ex, WebRequest request) {

        APIErrorResponse badRequest = new APIErrorResponse(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }
}
