package ru.mityushin.jobfinder.server.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.mityushin.jobfinder.server.util.exception.data.DataAlreadyExistsException;
import ru.mityushin.jobfinder.server.util.exception.data.DataNotFoundException;
import ru.mityushin.jobfinder.server.util.exception.data.MissingRequiredParametersException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static String createBodyWithMessage(String msg) {
        return "{\"message\": \"" + msg + "\"}";
    }

    private static HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, createBodyWithMessage(ex.getMessage()),
                createHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(MissingRequiredParametersException.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, createBodyWithMessage(ex.getMessage()),
                createHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, createBodyWithMessage(ex.getMessage()),
                createHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
