package com.sprint1.hcsapi.web;



import javax.validation.Valid;





import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.sprint1.hcsapi.domain.DiagnosticTest;
import com.sprint1.hcsapi.domain.TestResult;
import com.sprint1.hcsapi.service.DiagnosticTestService;
import com.sprint1.hcsapi.service.MapValidationErrorService;


@RestController
@RequestMapping("/api/Tests")
public class DiagnosticTestController {
	
	@Autowired
	private DiagnosticTestService diagnosticTestService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	
	
	/**
	 * create a response entity for addDiagnosticTest method  with test details and http status created
	 * @param diagnosticTest
	 * @param result
	 * @param dcId
	 */
	@PostMapping("/{dcId}/createTest")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addDiagnosticTest(@Valid @RequestBody DiagnosticTest diagnosticTest,BindingResult result,@PathVariable long dcId){
		ResponseEntity<?> errorMap=mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null)
			return errorMap;
		DiagnosticTest addTest=diagnosticTestService.addOrUpdateDiagnosticTest(diagnosticTest,dcId);
		return new ResponseEntity<DiagnosticTest>(addTest,HttpStatus.CREATED);
		
	}
	
	/**
	 * create response entity for deleteTestByName method with a message  and http status ok
	 * @param testName
	 */
	@DeleteMapping("/{testName}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteTestByName(@PathVariable String testName){
		diagnosticTestService.deleteTestByTestName(testName);
		return new ResponseEntity<String>("Diagnostic Test "+testName.toUpperCase()+" is deleted successfully",HttpStatus.OK);
	}
	
	/**
	 * create response entity for getAllTests method with all the test details and http status ok
	 * @return
	 */
	@GetMapping("/getAllTests")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Iterable<DiagnosticTest>> getAllTests(){
		Iterable<DiagnosticTest> testList=diagnosticTestService.getAllTests();
		return new ResponseEntity<Iterable<DiagnosticTest>>(testList,HttpStatus.OK);
	}
    
	/**
	 *
	 * @param testResult
	 * @param apId
	 * @param testId
	 * @return http status code for doTest method 
	 */
	@PostMapping("{apId}/{testId}/makeResult")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public HttpStatus doTest(@Valid @RequestBody TestResult testResult,@PathVariable long apId,@PathVariable long testId){
		diagnosticTestService.getResult(testResult,apId,testId);
		return HttpStatus.OK;
	}

}
