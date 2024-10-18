package com.example.employee_detail.controller;

import com.example.employee_detail.dto.DateFilter;
import com.example.employee_detail.dto.DateFilterWithEmployee;
import com.example.employee_detail.dto.EmployeeListTransfer;
import com.example.employee_detail.model.Admin;
import com.example.employee_detail.model.Employee;
import com.example.employee_detail.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/employeedetails")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("xlFile") MultipartFile file) throws IOException {
        System.out.println(" API IS HIT");
        System.out.println("file " + file);
        try {
            return employeeService.saveToDB(file);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<?> getAllData(@PathVariable int page){
        return employeeService.allData(page);
    }

    @GetMapping("/employeeId/{id}")
    public ResponseEntity<?> getAllDataWithEmpId(@PathVariable int id){
        return employeeService.allDataWithEmpId(id);
    }

    @GetMapping("/cardId/{id}")
    public ResponseEntity<?>  getAllDataWithCardID(@PathVariable int id){
        return employeeService.allDataWithCardId(id);
    }

    @PostMapping("/date")
    public ResponseEntity<?> filterDataWithDate(@RequestBody DateFilter dateObj){
        return employeeService.dateFilter(dateObj);
    }

    @PostMapping("/dataWithEmployeeFilter")
    public ResponseEntity<?> filterDataWithDateAndEmployee(@RequestBody DateFilterWithEmployee data){
        System.out.println("Inside the dataWithEmployeeFilter");
        return employeeService.dateFilterWithEmployee(data);
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll(@RequestBody Admin admin){
        return employeeService.deleteAll(admin);
    }

    @DeleteMapping("employeeId/{id}")
    public ResponseEntity<?> deleteByEmployeeId(@PathVariable int id){
        return employeeService.deleteByEmployeeId(id);
    }

    @DeleteMapping("/cardId/{id}")
    public ResponseEntity<?> deleteByCardId(@PathVariable int id){
        return employeeService.deleteByCardId(id);
    }

}
