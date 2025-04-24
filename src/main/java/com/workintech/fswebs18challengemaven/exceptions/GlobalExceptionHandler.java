package com.workintech.fswebs18challengemaven.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardException.class)
    public ResponseEntity<Object> handleCardNotFoundException(CardException ex) {
        // JSON yanıtının içeriğini oluşturuyoruz
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus().value(), "CardException");

        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    // Genel hatalar için başka bir exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        // JSON yanıtının içeriğini oluşturuyoruz
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "RuntimeException");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Hata yanıtı yapısı (ErrorResponse)
    public static class ErrorResponse {
        private String message;
        private int statusCode;
        private String errorType;

        public ErrorResponse(String message, int statusCode, String errorType) {
            this.message = message;
            this.statusCode = statusCode;
            this.errorType = errorType;
        }

        public String getMessage() {
            return message;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getErrorType() {
            return errorType;
        }
    }
}
