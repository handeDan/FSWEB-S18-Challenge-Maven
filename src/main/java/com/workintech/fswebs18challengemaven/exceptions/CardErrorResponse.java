package com.workintech.fswebs18challengemaven.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CardErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public CardErrorResponse(String expectedMessage) {
        this.status = 400;
        this.message = expectedMessage;
        this.timestamp = LocalDateTime.now();
    }
}
