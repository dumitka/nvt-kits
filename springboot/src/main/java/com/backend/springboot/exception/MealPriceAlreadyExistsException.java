package com.backend.springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "MealPrice already exists.")
public class MealPriceAlreadyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MealPriceAlreadyExistsException(String message) {
		super(message);
	}
}
