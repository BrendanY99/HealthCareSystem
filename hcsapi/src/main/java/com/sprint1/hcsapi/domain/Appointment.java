package com.sprint1.hcsapi.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;

	private String approvalStatus;

	
	/*
	 * This variable user is mapped many to one to the Users
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="user_id",updatable=false,nullable=false)
	@JsonIgnore
	private Users user;	
	
	/*
	 * This variable diagnosticCenter is mapped many to one to the Diagnostic Center
	 */
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="dc_id",updatable=false,nullable=false)
	@JsonIgnore
	private DiagnosticCenter diagnosticCenter;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="test_id",nullable=false)
	private DiagnosticTest diagnosticTest;

	@OneToOne(cascade=CascadeType.ALL,mappedBy="appointment")
	private TestResult testResult;
	
	private String testName;

	public Appointment() {
		super();
		this.approvalStatus="Waiting for Approval";
	}
	
	
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
