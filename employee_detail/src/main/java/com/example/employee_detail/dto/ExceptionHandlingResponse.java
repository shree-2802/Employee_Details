package com.example.employee_detail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionHandlingResponse {
    private HttpStatus status;
    private String message;
}
