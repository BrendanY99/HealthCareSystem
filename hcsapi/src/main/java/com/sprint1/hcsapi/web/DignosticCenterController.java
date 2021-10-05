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
	DiagnosticCenterService  dcs;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	/*
	 * Method to get all Diagnostic Centers
	 */
	@GetMapping("/all")
	public Iterable<DiagnosticCenter> getAllDisgnosticCenters()
	{
		return dcs.findAll();
	}
	
	/*
	 * Method to create or update Diagnostic Center 
	 */
	@PostMapping("")
	public ResponseEntity<?> createNewDC(@Valid @RequestBody DiagnosticCenter dc, BindingResult result) throws DiagnosticCenterException{
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		DiagnosticCenter newdc = dcs.saveOrUpdate(dc);
		
		return new ResponseEntity<DiagnosticCenter>(newdc, HttpStatus.CREATED);
	}
	/*
	 * Delete the Diagnostic Center by its dcID
	 * Here dcID is a path variable
	 */
	@DeleteMapping("/{dcID}")
	public ResponseEntity<?> deleteProject(@PathVariable Long dcID){
		dcs.deleteDiagnosticCenterBydcId(dcID);
		return new ResponseEntity<String>("DC with id : '"+dcID+"' is deleted.",HttpStatus.OK);
	}
	/*
	 * This method will give Diagnostic Center based on its dcID
	 * Here dcID is a path variable
	 */
	@GetMapping("/{dcID}")
	public ResponseEntity<?> findDC(@PathVariable Long dcID){
		DiagnosticCenter dc = dcs.findDiagnosticCenter(dcID);
		return new ResponseEntity<DiagnosticCenter>(dc, HttpStatus.OK);
	}
	
}
