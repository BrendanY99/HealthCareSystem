package com.sprint1.hcsapi.service;

import com.sprint1.hcsapi.domain.DiagnosticTest;

/**
 * This interface will hold the functionality for
 * DiagnosticTest related Business logic 
 **/
public interface DiagnosticTestService {
	
	/**
	 * This add method will add the diagnostic test if id not provided otherwise update
	 * @param diagnosticTest to be added or updated
	 * @return the added or updated diagnostic test
	 */
	public DiagnosticTest addOrUpdateDiagnosticTest(DiagnosticTest diagnosticTest);
	
	/**
	 * This get method will return all the available diagnostic tests
	 * @return all available diagnostic tests
	 */
	public Iterable<DiagnosticTest> getAllTests();
	
	/**
	 * This find method will find the diagnostic test for the respective testName
	 * @param testName to find diagnostic test
	 * @return the found diagnostic test
	 */
	public DiagnosticTest findByTestName(String testName);
	
	/**
	 *  This delete method will delete the diagnostic test for the respective testName
	 * @param testName
	 */
	public void deleteTestByTestName(String testName);
	
}
