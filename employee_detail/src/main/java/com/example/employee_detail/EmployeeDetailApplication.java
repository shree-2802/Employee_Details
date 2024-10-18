package com.example.employee_detail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class EmployeeDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetailApplication.class, args);
		System.out.println("Hello love");
		SimpleDateFormat date=new SimpleDateFormat("dd/MM/yyyy");
//		System.out.println(date.parse("11/09/1971"));
	}

}
