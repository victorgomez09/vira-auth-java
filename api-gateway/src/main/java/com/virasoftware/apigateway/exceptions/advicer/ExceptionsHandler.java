package com.virasoftware.apigateway.exceptions.advicer;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virasoftware.common.dto.ResponseError;
import com.virasoftware.common.exception.UnauthorizedException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ResponseError> handleUnauthorizedException(UnauthorizedException ex) {
        ResponseError responseError = new ResponseError(ex.getMessage(), Instant.now(), 401);
        
        return new ResponseEntity<>(responseError, HttpStatus.UNAUTHORIZED);
    }

}
