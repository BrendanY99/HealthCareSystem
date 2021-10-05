package com.sprint1.hcsapi.exception;

/**
 * This class creates the responses for exceptions generated due to appointment ID
 */
public class AppointmentIDExceptionResponse {
	private String id;

	public AppointmentIDExceptionResponse(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
}
