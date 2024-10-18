package com.example.employee_detail.controllerAdvice;

import com.example.employee_detail.dto.AdminLoginResponse;
import com.example.employee_detail.dto.ExceptionHandlingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionHandlingResponse> methodArgumentInvalidException(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body(new ExceptionHandlingResponse(HttpStatus.BAD_REQUEST,ex.getMessage()));
    }
}
