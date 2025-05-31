package com.fiap.globalsolution1.infra.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class RestErrorMessage {

    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

    public RestErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
