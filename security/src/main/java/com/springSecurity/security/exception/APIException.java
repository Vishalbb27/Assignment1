package com.springSecurity.security.exception;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException{
	private HttpStatus status;
	private String message;
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public APIException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	
}
