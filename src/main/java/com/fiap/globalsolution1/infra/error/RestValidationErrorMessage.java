package com.fiap.globalsolution1.infra.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RestValidationErrorMessage {

    private HttpStatus status;
    private LocalDateTime timestamp;
    private List<String> details;

    public RestValidationErrorMessage(HttpStatus status, List<String> details) {
        this.status = status;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }
}
