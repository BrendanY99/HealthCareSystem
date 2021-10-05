package com.sprint1.hcsapi.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class DiagnosticCenter {

	/*
	 * This is Id of Diagnostic center
	 * This is primary key, it is auto generated and unique one also
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dcID;
	
	/*
	 * This is Diagnostic center name
	 */
	//@NotBlank(message="dcName is required")
	private String dcName;
	
	/*
	 * This is Diagnostic center contact details
	 */
	private Long dcContactNo;

	/*
	 * This is another email, another thing for contact Diagnostic center
	 */
	private String dcEmail;
	

	/*
	 * Each Appoinment is connected to Diagnostic center via one to many relations
	 * means 1 Diagnostic center can have any number of appoinments
	 */
	@OneToMany(cascade=CascadeType.ALL,mappedBy="diagnosticCenter")
	private List<Appointment> appointments=new ArrayList<>();
	

	/*
	 * Address of Diagnostic center
	 */
	//@NotBlank(message="dcAddress is required")
	private String dcAddress;
	
	
	/*
	 * Constructor - default and parameterised
	 */
	public DiagnosticCenter() {
		
	}

	public DiagnosticCenter(long dcID, @NotBlank(message = "dcName is required") String dcName,
			@NotBlank(message = "dcContactNo is required") Long dcContactNo, String dcEmail,
			@NotBlank(message = "dcAddress is required") String dcAddress) {
		super();
		this.dcID = dcID;
		this.dcName = dcName;
		this.dcContactNo = dcContactNo;
		this.dcEmail = dcEmail;
		this.dcAddress = dcAddress;
	}

	/*
	 * Getters And setters
	 */
	public long getDcID() {
		return dcID;
	}

	public void setDcID(long dcID) {
		this.dcID = dcID;
	}

	public String getDcName() {
		return dcName;
	}

	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	public Long getDcContactNo() {
		return dcContactNo;
	}

	public void setDcContactNo(Long dcContactNo) {
		this.dcContactNo = dcContactNo;
	}

	public String getDcEmail() {
		return dcEmail;
	}

	public void setDcEmail(String dcEmail) {
		this.dcEmail = dcEmail;
	}

	

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public String getDcAddress() {
		return dcAddress;
	}

	public void setDcAddress(String dcAddress) {
		this.dcAddress = dcAddress;
	}
}
