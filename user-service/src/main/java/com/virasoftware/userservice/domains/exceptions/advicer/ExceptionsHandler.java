package com.virasoftware.userservice.domains.exceptions.advicer;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virasoftware.common.dto.ResponseError;
import com.virasoftware.common.exception.ConflictException;
import com.virasoftware.common.exception.UnprocessableEntityException;
import com.virasoftware.userservice.domains.exceptions.UserNotFoundException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ResponseError> handleUserNotFoundException(UserNotFoundException e) {
        ResponseError response = new ResponseError(e.getMessage(), Instant.now(), 404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<ResponseError> handleUsernameOrEmailAlreadyExistsException(ConflictException e) {
        ResponseError response = new ResponseError(e.getMessage(), Instant.now(), 409);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UnprocessableEntityException.class})
    public ResponseEntity<ResponseError> handleInvalidRequestException(UnprocessableEntityException e) {
        ResponseError response = new ResponseError(e.getMessage(), Instant.now(), 403);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}
