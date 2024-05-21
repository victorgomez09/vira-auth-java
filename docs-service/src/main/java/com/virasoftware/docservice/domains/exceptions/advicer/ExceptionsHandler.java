package com.virasoftware.docservice.domains.exceptions.advicer;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virasoftware.common.dto.ResponseError;
import com.virasoftware.common.exception.ConflictException;
import com.virasoftware.common.exception.UnprocessableEntityException;
import com.virasoftware.docservice.domains.exceptions.PermissionsException;
import com.virasoftware.docservice.domains.exceptions.NotFoundException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ResponseError> handleSpaceNotFoundException(NotFoundException e) {
        ResponseError response = new ResponseError(e.getMessage(), Instant.now(), 404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ PermissionsException.class })
    public ResponseEntity<ResponseError> handleNotPermissionsException(PermissionsException e) {
        ResponseError response = new ResponseError(e.getMessage(), Instant.now(), 403);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ ConflictException.class })
    public ResponseEntity<ResponseError> handleSpaceNameOrCodeAlreadyExistsException(ConflictException e) {
        ResponseError response = new ResponseError(e.getMessage(), Instant.now(), 409);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ UnprocessableEntityException.class })
    public ResponseEntity<ResponseError> handleInvalidRequestException(UnprocessableEntityException e) {
        ResponseError response = new ResponseError(e.getMessage(), Instant.now(), 403);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}
