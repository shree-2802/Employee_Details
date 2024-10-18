package com.example.employee_detail.dto;

import com.example.employee_detail.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListTransfer {
    private HttpStatus status;
    private String message;
    private Content content;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Content{
        private Boolean isEnd;
        private List<Employee> employeeList;
    }

    public EmployeeListTransfer(HttpStatus status,String message,Boolean isEnd,List<Employee> employeeList){
        this.status=status;
        this.message=message;
        this.content=new Content(isEnd,employeeList);
    }
}
