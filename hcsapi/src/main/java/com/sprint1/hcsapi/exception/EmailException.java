package com.sprint1.hcsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**This is user defined exception class.
 * @author devendra
 *This is used to get message from super class
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailException extends RuntimeException {
	
	/**
	 * 
	 */
  private static final long serialVersionUID = 1L;

	/*
	 * This will create EmailException object without error message
	 */
  public EmailException() {
	  
  }
  /*
   * This will create EmailException object with error message.
   */
  
  public EmailException(String message) {
	  super(message);
  }
	
}
