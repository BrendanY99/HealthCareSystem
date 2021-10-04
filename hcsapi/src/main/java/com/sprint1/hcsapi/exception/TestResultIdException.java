package com.sprint1.hcsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TestResultIdException extends RuntimeException{
	public TestResultIdException() {
		super();
	}
	
	public TestResultIdException(String msg) {
		super(msg);
	}

}
