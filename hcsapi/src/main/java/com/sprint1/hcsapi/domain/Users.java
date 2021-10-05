package com.sprint1.hcsapi.domain;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


/** This Users class is a domain,it contains data related to user  and it will be moving layer to layer.
 * @author devendra
 *
 */

@Entity
public class Users {
	
	/**
	 * This is id of user, it is primary key and auto generated.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * This is email of user.
	 */
	@NotBlank(message="Email is required")
	@Column(unique=true,updatable=false)
	private String email;
	
	/**
	 * This is username of user it should be unique and cannot be updated.
	 */
	@Column(unique=true ,updatable=false)
	private String username;
	
	/**
	 * This is password of user
	 */
	@NotBlank(message="Password is required")
	private String password;
	
	/**
	 * This creates and maintains separate table called user_roles.
	 */
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinColumn(name="id")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Role> roles=new ArrayList<>();
   
	
	/**
	 * This is name of user
	 */
	@NotBlank(message="Name is required")
	private String name;
	
	/**
	 * This is gender of user
	 */
	@NotBlank(message="Gender is required")
	private String gender;
	
	/**
	 * This is phone number of user
	 * it should have 10 digits
	 */
	@Size(min=10,max=10,message="Phone number should have 10 digits")
	@NotBlank(message="Phone Number is required")
	@Column(unique=true)
	private String phoneNo;
	
	/**
	 * This is age of user
	 */
	private Integer age;
	
	/**
	 * OneToMany mapping with Appointment
	 */
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	private List<Appointment> appointments=new ArrayList<>();
	
	/**
	 * default constructor
	 */
	public Users() {
		super();
		
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	

}
