package com.springSecurity.security.exception;

public class UsernameNotFoundException extends RuntimeException  {
	public UsernameNotFoundException(String message) {
		super(message);
	}
}
