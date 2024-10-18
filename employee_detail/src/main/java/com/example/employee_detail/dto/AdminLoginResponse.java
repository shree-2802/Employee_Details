package com.example.employee_detail.dto;

import com.example.employee_detail.enums.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminLoginResponse {
    private HttpStatus status;
    private String message;
    public AdminLoginResponse(HttpStatus httpStatus,LoginResponse loginResponse){
       this.status=httpStatus;
       this.message=loginResponse.toString();
    }
}
