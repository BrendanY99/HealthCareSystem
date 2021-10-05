package com.sprint1.hcsapi.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This Appointment class is used by the patient to create the appointment
 *
 */

@Entity
public class Appointment {
	
	/**
	 * Id of the appointment
	 * primary key and auto generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 *  date of the appointment
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;

	/**
	 *  approvalStatus of the appointment
	 */
	private String approvalStatus;

	/**
	 *  name of the test for which the patient is making appointment
	 */
	private String testName;
	
	/**
	 * This user variable maps many appointments to one user
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="user_id",updatable=false,nullable=false)
	@JsonIgnore
	private Users user;	
	
	/**
	 * This diagnosticCenter variable maps many appointments to one Diagnostic Center
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="dc_id",updatable=false,nullable=false)
	@JsonIgnore
	private DiagnosticCenter diagnosticCenter;
	
	/**
	 * This diagnosticTest variable maps one appointment to one Diagnostic Test 
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="test_id",nullable=false)
	private DiagnosticTest diagnosticTest;

	/**
	 * This testResult variable maps one appointment to one Test Result
	 */
	@OneToOne(cascade=CascadeType.ALL,mappedBy="appointment")
	private TestResult testResult;
	
	/**
	 * Constructor without arguments
	 * sets the approvalStatus to "Waiting for Approval" on creation of appointment
	 */
	public Appointment() {
		super();
		this.approvalStatus="Waiting for Approval";
	}
	
	/**
	 * Getters and Setters
	 */
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getApprovalStatus() {
		return approvalStatus;
	}
	
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUser(Users user) {
		this.user = user;
	}
	
	public TestResult getTestResult() {
		return testResult;
	}
	
	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}
	
	public DiagnosticTest getDiagnosticTest() {
		return diagnosticTest;
	}
	
	public void setDiagnosticTest(DiagnosticTest diagnosticTest) {
		this.diagnosticTest = diagnosticTest;
	}
	
	public DiagnosticCenter getDiagnosticCenter() {
		return diagnosticCenter;
	}
	
	public void setDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		this.diagnosticCenter = diagnosticCenter;
	}


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}
	
}
