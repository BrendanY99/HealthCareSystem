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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.hcsapi.domain.TestResult;
import com.sprint1.hcsapi.service.MapValidationErrorService;
import com.sprint1.hcsapi.service.TestResultService;

/**
 * This is TestResultController and used to handle front end calls and generate json response.
 * @author Atharva
 *
 */
@RestController
@RequestMapping("/api/TestResults")
public class TestResultController {
	
	@Autowired
	private TestResultService resultservice;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewResult(@Valid @RequestBody TestResult result,BindingResult bResult){
		ResponseEntity<?> errorMap=mapValidationErrorService.MapValidationError(bResult);
		if(errorMap!=null) return errorMap;
		TestResult savedResult = resultservice.saveorUpdate(result);
		
		return new ResponseEntity<TestResult>(savedResult,HttpStatus.CREATED);
		
	}
	
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTestResult(@PathVariable long id){
		resultservice.removeTestResultById(id);
		return new ResponseEntity<String>("Test Result with id: " +id + "is deleted",HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTestResultById(@PathVariable long id){
		TestResult result = resultservice.viewTestResultById(id);
		return new ResponseEntity<TestResult>(result,HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/AllTestResults")
	public Iterable<TestResult> getAllTestResults(){
		return resultservice.viewAllTestResults();
	}
	
	
	
}

