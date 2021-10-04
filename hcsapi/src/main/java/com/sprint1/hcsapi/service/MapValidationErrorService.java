package com.sprint1.hcsapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface MapValidationErrorService {

	/**This method will verify if there is error in  data of user object
	 * @param result
	 * @return it will return ResponseEntity with error information if there is error or return null 
	 */
	public ResponseEntity<?> mapValidationError(BindingResult result);
}

