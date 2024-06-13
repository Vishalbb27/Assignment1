package com.springSecurity.security.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity.security.dto.UserDto;
import com.springSecurity.security.service.EmployeeService;
import com.springSecurity.security.service.UserService;

@RestController
//@PreAuthorize("hasRole('ADMIN')")//Method level security
public class Controller {
	
	EmployeeService employeeService;
	UserService userService;
	public Controller(EmployeeService employeeService,UserService userService) {
		this.employeeService= employeeService;
		this.userService = userService;
	}
//	@GetMapping
//	public ResponseEntity<String> getUsers() {
//		return new ResponseEntity<>("hello World",HttpStatus.OK);
//	}
	
	
	@GetMapping("/employee")
	public CompletableFuture<String> addEmployees(){
		return (employeeService.AddEmployee());
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getUsers() {
		List<UserDto> users = userService.getAllUsers();
		
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
}
