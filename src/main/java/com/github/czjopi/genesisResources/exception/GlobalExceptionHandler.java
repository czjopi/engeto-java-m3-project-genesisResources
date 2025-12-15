package com.github.czjopi.genesisResources.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.github.czjopi.genesisResources.model.ErrorResponseDto;

/**
 * Global exception handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** Handles UserNotFoundException and returns a 404 Not Found response. */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(ex.getMessage()));
    }

    /** Handles DuplicatePersonIdException and returns a 409 Conflict response. */
    @ExceptionHandler(DuplicatePersonIdException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicatePersonId(DuplicatePersonIdException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDto(ex.getMessage()));
    }

    /** Handles all other exceptions and returns a 500 Internal Server Error response. */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralException(Exception ex) {
        log.error("An unexpected error occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto("An unexpected error occurred."));
    }
}
