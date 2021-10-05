package com.sprint1.hcsapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.hcsapi.domain.TestResult;
import com.sprint1.hcsapi.exception.TestResultIdException;
import com.sprint1.hcsapi.repository.AppointmentRepository;
import com.sprint1.hcsapi.repository.TestResultRepository;
import com.sprint1.hcsapi.service.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService {
	

	@Autowired
	private TestResultRepository resultRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	/**
	 * This method is overriding the saveorUpdate method of Test Result Service
	 * This method is to create and update test result
	 */
	@Override
	public TestResult saveorUpdate(TestResult result) {
		try {
			if(result.getId()!=null && resultRepository.existsById(result.getId())) {
				TestResult oldResult=resultRepository.findById(result.getId()).get();
				if(result.getCondition()!=null) {
					oldResult.setCondition(result.getCondition());
					
				}
				if(result.getTestReading()!=0.0) {
					oldResult.setTestReading(result.getTestReading());
				}
				return resultRepository.save(oldResult);
			}
			return resultRepository.save(result);
			
		}
		catch(Exception e) {
			throw new TestResultIdException("TestResultId is"+result.getId()+"already exists");
		}
			
	}


	/**
	 * This method is overriding the removeTestResultById method of Test Result Service
	 * This method is used to remove the test result by test result id
	 */
	@Override
	public void removeTestResultById(long id) {
		TestResult result= resultRepository.findTestResultById(id);
		resultRepository.delete(result);
		
	}

	/**
	 * This method is overriding the viewTestResultById method of Test Result Service
	 * This method is used to view the test result by test result id
	 */
	@Override
	public TestResult viewTestResultById(long apId) {
		TestResult result = appointmentRepository.findById(apId).get().getTestResult();
		if(result==null)
		{
			throw new TestResultIdException("TestResultId is"+result.getId()+"does not exists");
			
		}
		return result;
	}

	/**
	 * This method is overriding the viewAllTestResults method of Test Result Service
	 * This method is used by the admin to view all test results
	 */
	@Override
	public Iterable<TestResult> viewAllTestResults() {
		
		return resultRepository.findAll();
	}

}
