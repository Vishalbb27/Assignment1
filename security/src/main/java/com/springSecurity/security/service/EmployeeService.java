package com.springSecurity.security.service;

import java.util.concurrent.CompletableFuture;

public interface EmployeeService {
	public CompletableFuture<String> AddEmployee();
}
