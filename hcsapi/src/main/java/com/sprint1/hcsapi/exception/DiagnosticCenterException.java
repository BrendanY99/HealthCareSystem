package com.sprint1.hcsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DiagnosticCenterException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * This exception class is to create exception handling for data related to Diagnostic center
	 * Whatever is going wrong call this class and put exception message in 'String msg' while calling
	 * Call default constructor to throw without any message  
	 */
	public DiagnosticCenterException()
	{
		super();
	}
	
	public DiagnosticCenterException(String msg)
	{
		super(msg);
	}
	
}
