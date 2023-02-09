package ru.practicum.explore.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice("ru.practicum")

public class Handler {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleBadRequest(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ErrorResponse(new String[]{}, exception.getMessage(),
                "For the requested operation the conditions are not met.",
                "FORBIDDEN"), HttpStatus.BAD_REQUEST);
    }

    //403
    @ExceptionHandler(ForbiddenEx.class)
    public ResponseEntity<Object> handleBadRequest(ForbiddenEx exception) {
        return new ResponseEntity<>(new ErrorResponse(new String[]{}, exception.getMessage(),
                "For the requested operation the conditions are not met.",
                "FORBIDDEN"), HttpStatus.FORBIDDEN);
    }

    //404
    @ExceptionHandler(NotFoundEx.class)
    public ResponseEntity<Object> handleNotFound(NotFoundEx exception) {
        return new ResponseEntity<>(new ErrorResponse(new String[]{},
                exception.getMessage(), "The required object was not found", "NOT_FOUND"),
                HttpStatus.NOT_FOUND);
    }

    //409
    @ExceptionHandler(ConflictEx.class)
    public ResponseEntity<Object> handleConflict(ConflictEx exception) {
        return new ResponseEntity<>(new ErrorResponse(new String[]{}, exception.getMessage(),
                "Integrity constraint has been violated", "NOT_FOUND"), HttpStatus.CONFLICT);
    }

    //500
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<Object> handleInternalServerError(HttpServerErrorException.InternalServerError exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(),
                "Error occurred", "INTERNAL_SERVER_ERROR"), HttpStatus.CONFLICT);
    }

    private class ErrorResponse {
        @JsonProperty("errors")
        private String[] errors;
        @JsonProperty("message")
        private String message;
        @JsonProperty("reason")
        private String reason;
        @JsonProperty("status")
        private String status;
        @JsonProperty("timestamp")
        private String timestamp;

        public ErrorResponse(String[] errors, String message, String reason, String status) {
            this.errors = errors;
            this.message = message;
            this.reason = reason;
            this.status = status;
            this.timestamp = LocalDateTime.now().format(FORMATTER);
        }

        public ErrorResponse(String message, String reason, String status) {
            this.errors = errors;
            this.message = message;
            this.reason = reason;
            this.status = status;
            this.timestamp = LocalDateTime.now().format(FORMATTER);
        }
    }
}
