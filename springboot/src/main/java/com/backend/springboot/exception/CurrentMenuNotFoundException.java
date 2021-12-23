package com.backend.springboot.exception;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Current menu not found.")
public class CurrentMenuNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CurrentMenuNotFoundException(String message) {
		super(message);
	}
	
}
