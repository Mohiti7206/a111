package com.idsargus.akpmsadminservice.Mvc.Exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String BASE_PACKAGE = "com.idsargus.akpmsadminservice.Mvc"; // Your project's package name

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> handleDatabaseException(DataAccessException ex) {
        return buildErrorResponse("Database Error", ex);
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity<Map<String, String>> handleSQLSyntaxError(SQLSyntaxErrorException ex) {
        return buildErrorResponse("SQL Syntax Error", ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException ex) {
        return buildErrorResponse("Entity Not Found", ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        return buildErrorResponse("Unexpected Error", ex);
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(String errorType, Exception ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("errorType", errorType);
        errorDetails.put("message", ex.getMessage());

        // Extract the first occurrence of a class from the BASE_PACKAGE
        StackTraceElement[] stackTrace = ex.getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().startsWith(BASE_PACKAGE)) {
                errorDetails.put("className", element.getClassName());
                errorDetails.put("methodName", element.getMethodName());
                errorDetails.put("lineNumber", String.valueOf(element.getLineNumber()));
                break; // Stop at the first occurrence in your project
            }
        }
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
