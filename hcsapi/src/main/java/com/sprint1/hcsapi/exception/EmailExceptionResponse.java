package com.sprint1.hcsapi.exception;

/**
 * 
 * @author devendra
 *
 */
public class EmailExceptionResponse {
	
  private String email;
  
  public EmailExceptionResponse(String email) {
		super();
		this.email = email;
	}

  public String getEmail() {
	return email;
   }

  public void setEmail(String email) {
	this.email = email;
   }
  
}
