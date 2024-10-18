package com.example.employee_detail.controller;

import com.example.employee_detail.model.Admin;
import com.example.employee_detail.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/employeedetails")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        System.out.println("Here's the obj");
        this.adminService=adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin){
        System.out.print("Hello clicked");
        return adminService.login(admin);
    }

    @PostMapping("/setuser")
    public ResponseEntity<?> setuser(@Valid @RequestBody Admin admin){
        System.out.println("Here;s the set");
        return adminService.saveUser(admin);
    }
}
