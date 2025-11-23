package com.iotwastecollection.collection.exception;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFound(EntityNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.CONFLICT.value());
        error.put("error", "Data Integrity Violation");
        
        String message = ex.getMessage();
        Throwable cause = ex.getCause();
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) cause;
            String constraintName = cve.getConstraintName();
            message = "Database constraint violation" + (constraintName != null ? ": " + constraintName : "");
        } else if (cause instanceof SQLIntegrityConstraintViolationException) {
            SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) cause;
            message = "Database constraint violation: " + sqlEx.getMessage();
        }
        error.put("message", message);
        Throwable rootCause = ex.getRootCause();
        if (rootCause != null) {
            error.put("details", rootCause.getMessage());
        } else {
            error.put("details", ex.getMessage());
        }
        
        ex.printStackTrace(); // Log for debugging
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Bad Request");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed");
        response.put("message", "Invalid input data");
        response.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred");
        error.put("exception", ex.getClass().getName());
        
        // Include root cause if available
        Throwable rootCause = ex;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }
        if (rootCause != ex) {
            error.put("rootCause", rootCause.getClass().getSimpleName() + ": " + rootCause.getMessage());
        }
        
        // Log full stack trace for debugging
        System.err.println("=== EXCEPTION STACK TRACE ===");
        ex.printStackTrace();
        System.err.println("=============================");
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
