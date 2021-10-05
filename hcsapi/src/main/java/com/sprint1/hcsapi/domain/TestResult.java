package com.sprint1.hcsapi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This TestResult Class is a domain, which represents data and it will be moving
 * layer to layer.
 * 
 * @author Atharva
 *
 */
@Entity
public class TestResult {
	/**
	 * id of the TestResult and auto generated
	 */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	/**
	 * TeastReading of the patient
	 */
	
	private double testReading;
	/**
	 * Condition of the patient
	 */
	
	private String condition;
	
	/**
	 * Default constructor
	 */
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="appointment_id",nullable=false)
	@JsonIgnore
	private Appointment appointment;
	
	public TestResult() {
		super();
	}
	
	/**
	 * Getters and setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getTestReading() {
		return testReading;
	}
	public void setTestReading(double testReading) {
		this.testReading = testReading;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	
	

}
