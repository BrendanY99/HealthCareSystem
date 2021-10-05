package com.sprint1.hcsapi.service;

import com.sprint1.hcsapi.domain.Appointment;

/**
 * This interface is to declare required methods
 * All methods here will be implemented at another layer
 * All methods contain code related to business logic  
 */
public interface AppointmentService {

	/**
	 * Method to save or create an Appointment
	 */
	public Appointment save(String token,Appointment appointment,long dcId);
	
	/**
	 * Method to update or make changes in an Appointment
	 */
	public Appointment validate(Appointment appointment);
	
	/**
	 * Method to view Appointment by id
	 */
	public Appointment viewAppointmentById(long id);
	
	/**
	 * Method to view all Appointments
	 */
	public Iterable<Appointment> viewAllAppointments();
	
	/**
	 * Method to remove an Appointment
	 */
	public void removeAppointmentById(long id);
}
