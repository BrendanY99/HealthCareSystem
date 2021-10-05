package com.sprint1.hcsapi.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.sprint1.hcsapi.domain.DiagnosticTest;

@Repository
public interface DiagnosticTestRepository extends CrudRepository<DiagnosticTest, Long> {
	 
	
	 /**
	  * This method will find the diagnostic test based on the test name
	 * @param testName to be find
	 * @return the found DiagnosticTest
	 */
	DiagnosticTest findByTestName(String testName);
	 

	 
}
