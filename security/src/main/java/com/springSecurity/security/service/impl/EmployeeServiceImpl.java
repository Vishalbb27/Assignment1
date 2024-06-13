package com.springSecurity.security.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.springSecurity.security.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	@Async("taskExecutor")
	public CompletableFuture<String> AddEmployee() {
		try {
			System.out.println(Thread.currentThread().getId());
			Thread.sleep(30000);
			System.out.println("Completed");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture("Employee Added");
	}

}
