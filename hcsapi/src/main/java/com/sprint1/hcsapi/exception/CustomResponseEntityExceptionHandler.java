package com.sprint1.hcsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler
	public final ResponseEntity<Object> handleTestNameException(TestNameException ex, WebRequest request)
	{
		TestNameExceptionResponse exceptionResonse=new TestNameExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResonse,HttpStatus.BAD_REQUEST);
	}

}
