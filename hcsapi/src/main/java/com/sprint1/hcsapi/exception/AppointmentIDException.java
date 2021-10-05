package com.sprint1.hcsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is user defined exception class.
 * This exception class is to create exception handling for data related to Appointment
 * The parameterized constructor returns message as per String msg
 * Call default constructor to throw without any message
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppointmentIDException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public AppointmentIDException() {
		super();
	}
	public AppointmentIDException(String msg) {
		super(msg);
	}
}
