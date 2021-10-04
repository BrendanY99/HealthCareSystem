package com.sprint1.hcsapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.hcsapi.domain.TestResult;

@Repository
public interface TestResultRepository extends CrudRepository<TestResult, Long> {
	TestResult findTestResultById(long id); 

}
