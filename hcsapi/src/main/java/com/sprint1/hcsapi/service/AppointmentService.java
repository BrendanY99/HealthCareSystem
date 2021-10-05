package com.sprint1.hcsapi.service;

import com.sprint1.hcsapi.domain.Appointment;

public interface AppointmentService {

	public Appointment save(String token,Appointment appointment,long dcId);
	
	public Appointment update(Appointment appointment,long testId);
	
	public Appointment viewAppointmentById(long id);
	
	public Iterable<Appointment> viewAllAppointments();
	
	public void removeAppointmentById(long id);
}
