package com.sprint1.hcsapi.exception;

public class TestNameExceptionResponse {
   private String testName;
   

	public TestNameExceptionResponse(String testName) {
		super();
		this.testName = testName;
    }

	public String getTestName() {
		return testName;
	}
	
	public void setTestName(String testName) {
		this.testName = testName;
	}
	   
}
