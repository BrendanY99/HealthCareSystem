package com.sprint1.hcsapi.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.hcsapi.domain.DiagnosticCenter;
import com.sprint1.hcsapi.exception.DiagnosticCenterException;
import com.sprint1.hcsapi.service.DiagnosticCenterService;
import com.sprint1.hcsapi.service.MapValidationErrorService;

@RestController
@RequestMapping("/api/dc")
public class DignosticCenterController {

	@Autowired
	DiagnosticCenterService  diagnosticCenterService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	/**
	 * Method to get all Diagnostic Centers
	 */
	@GetMapping("/all")
	public Iterable<DiagnosticCenter> getAllDisgnosticCenters()
	{
		return diagnosticCenterService.findAll();
	}

	/**
	 * Method to create or update Diagnostic Center 
	 */
	@PostMapping("")
	public ResponseEntity<?> createNewDC(@Valid @RequestBody DiagnosticCenter diagnosticCenter, BindingResult result) throws DiagnosticCenterException{
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		DiagnosticCenter newdiagnosticCenter = diagnosticCenterService.saveOrUpdate(diagnosticCenter);
		
		return new ResponseEntity<DiagnosticCenter>(newdiagnosticCenter, HttpStatus.CREATED);
	}
	
	/**
	 * Delete the Diagnostic Center by its dcID
	 * Here dcID is a path variable
	 */
	@DeleteMapping("/{dcID}")
	public ResponseEntity<?> deleteProject(@PathVariable Long dcID){
		diagnosticCenterService.deleteDiagnosticCenterById(dcID);
		return new ResponseEntity<String>("DC with id : '"+dcID+"' is deleted.",HttpStatus.OK);
	}
	
	/**
	 * This method will give Diagnostic Center based on its dcID
	 * Here dcID is a path variable
	 */
	@GetMapping("/{dcID}")
	public ResponseEntity<?> findDC(@PathVariable Long dcID){
		DiagnosticCenter diagnosticCenter = diagnosticCenterService.findDiagnosticCenter(dcID);
		return new ResponseEntity<DiagnosticCenter>(diagnosticCenter, HttpStatus.OK);
	}
	
}
