package com.blog.exceptions;

import com.blog.payloads.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFound ex){
        String msg = ex.getMessage();
        ApiResponse api = new ApiResponse(msg, false);
        return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> dataIntegrityViolationException(DataIntegrityViolationException ex){
        String msg = ex.getMessage();
        ApiResponse api = new ApiResponse(msg, false);
        return new ResponseEntity<>(api, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((err) -> {
            String field = ((FieldError)err).getField();
            String message = err.getDefaultMessage();

            response.put(field, message);
        });
        return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> NoResourceFoundException(NoResourceFoundException ex){
        String msg = ex.getMessage();
        ApiResponse api = new ApiResponse(msg, false);
        return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        String msg = ex.getMessage();
        ApiResponse api = new ApiResponse(msg, false);
        return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<?> JpaSystemException(JpaSystemException ex){
        String msg = ex.getMessage();
        return new ResponseEntity<>(new ApiResponse(msg, false), HttpStatus.BAD_REQUEST);
    }
}
