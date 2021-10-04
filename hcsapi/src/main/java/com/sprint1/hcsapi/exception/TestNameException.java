package com.sprint1.hcsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TestNameException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public TestNameException() {
    	super();
    }
    public TestNameException(String message) {
    	super(message);
    }
}
