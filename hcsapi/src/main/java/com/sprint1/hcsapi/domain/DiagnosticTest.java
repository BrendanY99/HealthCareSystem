package com.sprint1.hcsapi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This DiagnosticTest class is a domain which represents
 * tests that are available in the diagnostic center
 *
 */
@Entity
public class DiagnosticTest {
	
	/**
	 * Id of the diagnostic test
	 * primary key and auto generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 *  name of the test
	 */
	@NotBlank(message="Test name is required")
	@Column(unique = true,updatable = false)
	private String testName;

	/**
	 *  price value of the test
	 */
	@NotNull(message="Price of the test is required")
	private Double price;
	
	/**
	 *  normal range or level of the test
	 */
	@NotBlank(message="Normal value is required")
	private String normalValue;
	
	/**
	 * units in which a particular test is measured
	 */
	@NotBlank(message="units is required")
	private String units;
	
	private String testStatus;
	

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="dc_id",updatable=false,nullable=false)
	@JsonIgnore
	private DiagnosticCenter diagnosticCenter;
	
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="diagnosticTest")
//	@JoinColumn(name="appointment_id",nullable=false)
	@JsonIgnore
	private Appointment appointment;
	


	/**
	 * Constructor without arguments
	 */
	public DiagnosticTest() {
		super();
		this.testStatus="Available";
	}
	
	/**
	 * Getters and Setters
	 */
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setTestId(Long id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getNormalValue() {
		return normalValue;
	}
	public void setNormalValue(String normalValue) {
		this.normalValue = normalValue;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public DiagnosticCenter getDiagnosticCenter() {
		return diagnosticCenter;
	}

	public void setDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		this.diagnosticCenter = diagnosticCenter;
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	
	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
}
