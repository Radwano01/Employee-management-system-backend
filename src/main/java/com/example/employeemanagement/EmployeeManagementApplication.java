package com.example.employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan({"com.example.employeemanagement.Repository", "com.example.employeemanagement.Security"})
public class EmployeeManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}
}
