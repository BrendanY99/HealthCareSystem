package com.sprint1.hcsapi.service;

import com.sprint1.hcsapi.domain.TestResult;

/**
 * This TestResultService interface will hold the functionality for TestResult Related Business logic
 * @author Atharva
 *
 */
public interface TestResultService {
	/**
	 * This saveorUpdate method will save the TestResult if the id is not provided otherwise update
	 * @param test result to be saved or updated
	 * return the saved or updated test result
	 *
	 */
	
	public TestResult saveorUpdate(TestResult result);
	
	/**
	 *This removeTestResultById method will delete the test result when id of test result to be deleted is provided.
	 */
	public void removeTestResultById(long id);
	/**
	 * This viewTestResultById method will display the test result of a specific provided id.
	 */
	public TestResult viewTestResultById(long id);
	/**
	 * This viewAllTestResults method will display all the available test Results.
	 */
	public Iterable<TestResult> viewAllTestResults();
	
	
}
