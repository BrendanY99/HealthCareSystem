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


/**
 * This DiagnosticCenter class is a domain which comprises of all the details in 
 *
 */

@Entity
public class DiagnosticCenter {

	/*
	 * This is Id of Diagnostic center
	 * This is primary key, it is auto generated and unique one also
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/*
	 * This is Diagnostic center name
	 */
	private String name;
	
	/*
	 * This is Diagnostic center contact details
	 */
	private Long contactNo;

	/*
	 * This is another email, another thing for contact Diagnostic center
	 */
	private String email;
	
	/*
	 * Address of Diagnostic center
	 */
	private String address;
	
	
	/*
	 * Each Appoinment is connected to Diagnostic center via one to many relations
	 * means 1 Diagnostic center can have any number of appoinments
	 */
	@OneToMany(cascade=CascadeType.ALL,mappedBy="diagnosticCenter")
	private List<Appointment> appointments=new ArrayList<>();
	

	/**
	 * OneToMany mapping with Appointment
	 */
	@OneToMany(cascade=CascadeType.ALL,mappedBy="diagnosticCenter")
	private List<DiagnosticTest> diagnosticTest=new ArrayList<>();
	
	/*
	 * Constructor - default 
	 */
	public DiagnosticCenter() {
		
	}

	/*
	 * Constructor - parameterized
	 */
	public DiagnosticCenter(long id, @NotBlank(message = "Diagnostic Center name is required") String dcName,
			@NotBlank(message = "diagnostic Center contactNo is required") Long dcContactNo, String dcEmail,
			@NotBlank(message = "diagnostic Center Address is required") String dcAddress) {
		super();
		this.id = id;
		this.name = name;
		this.contactNo = contactNo;
		this.email = email;
		this.address = address;
	}

	/*
	 * Getters And setters
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public List<Appointment> getAppointments() {
		return appointments;
	}


	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<DiagnosticTest> getDiagnosticTest() {
		return diagnosticTest;
	}

	public void setDiagnosticTest(List<DiagnosticTest> diagnosticTest) {
		this.diagnosticTest = diagnosticTest;
	}

	
}
