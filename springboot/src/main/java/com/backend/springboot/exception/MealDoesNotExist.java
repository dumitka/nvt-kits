package com.backend.springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Meal not found.")
public class MealDoesNotExist extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MealDoesNotExist(String message) {
		super(message);
	}
}
