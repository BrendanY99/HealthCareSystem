package com.sprint1.hcsapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface MapValidationErrorService {
	public ResponseEntity<?> MapValidationError(BindingResult result);
}
