package com.virasoftware.authservice.exceptions.advicer;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virasoftware.common.dto.ResponseError;
import com.virasoftware.common.exception.ConflictException;
import com.virasoftware.common.exception.UnauthorizedException;
import com.virasoftware.common.exception.UnprocessableEntityException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<ResponseError> handleConflictException(ConflictException e) {
        ResponseError responseError = new ResponseError(e.getMessage(), Instant.now(), 409);
        return new ResponseEntity<>(responseError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {UnprocessableEntityException.class})
    public ResponseEntity<ResponseError> handleUnprocessableEntityException(UnprocessableEntityException e) {
        ResponseError responseError = new ResponseError(e.getMessage(), Instant.now(), 422);
        return new ResponseEntity<>(responseError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ResponseError> handleUnauthorizedException(UnauthorizedException e) {
        ResponseError responseError = new ResponseError(e.getMessage(), Instant.now(), 401);
        return new ResponseEntity<>(responseError, HttpStatus.UNAUTHORIZED);
    }

}
