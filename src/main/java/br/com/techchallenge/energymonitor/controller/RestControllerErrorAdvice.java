package br.com.techchallenge.energymonitor.controller;

import br.com.techchallenge.energymonitor.exception.ApiErrorResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static java.util.stream.Collectors.joining;

@RestControllerAdvice
public class RestControllerErrorAdvice {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var message = exception
            .getAllErrors()
            .stream()
            .map(ObjectError::getDefaultMessage)
            .collect(joining("\n"));

        var errorResponse = new ApiErrorResponse(
            message, 
            LocalDateTime.now(), 
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ApiErrorResponse> handleInvalidMessageException(HttpMessageNotReadableException exception) {
        var jsonMappingException = (JsonMappingException) exception.getCause();
        var errorField = jsonMappingException.getPath().get(0).getFieldName();
        var message = String.format("Campo %s está inválido. Tente Novamente com um valor correto.", errorField);
        var timestamp = LocalDateTime.now();
        var errorResponse = new ApiErrorResponse(message, timestamp,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        exception.printStackTrace();
        var message = exception.getMessage();
        var timestamp = LocalDateTime.now();
        var errorResponse = new ApiErrorResponse(message, timestamp,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseEntity<ApiErrorResponse> handleGenericException(Exception exception) {
        exception.printStackTrace();
        var message = exception.getMessage();
        var timestamp = LocalDateTime.now();
        var errorResponse = new ApiErrorResponse(message, timestamp, 
        HttpStatus.INTERNAL_SERVER_ERROR.value(), 
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return ResponseEntity.internalServerError().body(errorResponse);
    }    
}
