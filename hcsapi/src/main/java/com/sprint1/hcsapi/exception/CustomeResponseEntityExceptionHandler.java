package com.sprint1.hcsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * 
 * @author devendra
 *
 */
@ControllerAdvice
@RestController
public class CustomeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
   @ExceptionHandler
	public final ResponseEntity<Object> handleEmailException(EmailException ex,WebRequest request){
	   EmailExceptionResponse exceptionResponse = new EmailExceptionResponse(ex.getMessage());
	   return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
   }
   
}
