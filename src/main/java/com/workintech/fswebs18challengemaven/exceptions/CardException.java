package com.workintech.fswebs18challengemaven.exceptions;

import org.springframework.http.HttpStatus;

public class CardException extends RuntimeException{
    private final HttpStatus status;
    public CardException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
