package com.sprint1.hcsapi.exception;

import org.springframework.http.HttpStatus;

/**
 * This class is used to handle any type of exception in the entire program
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String message;
	private final HttpStatus httpStatus;
	
	public CustomException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
