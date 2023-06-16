package br.com.techchallenge.energymonitor.exception;

import java.time.LocalDateTime;

public record ApiErrorResponse (
    String message,
    LocalDateTime timestamp,
    int statusCode,
    String statusCodeMessage
) {}
