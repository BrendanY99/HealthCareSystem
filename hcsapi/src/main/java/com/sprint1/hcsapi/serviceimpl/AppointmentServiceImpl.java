package com.sprint1.hcsapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.hcsapi.domain.Appointment;
import com.sprint1.hcsapi.exception.AppointmentIDException;
import com.sprint1.hcsapi.repository.AppointmentRepository;
import com.sprint1.hcsapi.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	public Appointment save(Appointment appointment) {

			return appointmentRepository.save(appointment);

	}
	
	@Override
	public Appointment update(Appointment appointment) {
		try {
			if(appointment.getId()!=null && appointmentRepository.existsById(appointment.getId()))
			{
				Appointment oldAppointment=appointmentRepository.findById(appointment.getId()).get();
				if(appointment.getDate()!=null) {
					oldAppointment.setDate(appointment.getDate());
				}
				if(appointment.getApprovalStatus()!=null) {
					oldAppointment.setApprovalStatus(appointment.getApprovalStatus());
				}
				return appointmentRepository.save(oldAppointment);
			}
			return appointmentRepository.save(appointment);
		}
		catch(Exception e) {
			throw new AppointmentIDException("Appointment Id "+appointment.getId()+" already exists");
		}
	}
	
	
	@Override
	public Appointment viewAppointmentById(long id) {
		Appointment appointment=appointmentRepository.findById(id).get();
		if(appointment==null) {
			throw new AppointmentIDException("Appointment Id "+appointment.getId()+" does not exists");
		}
		return appointment;
	}

	@Override
	public Iterable<Appointment> viewAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public void removeAppointmentById(long id) {
		Appointment appointment=appointmentRepository.findById(id).get();
		appointmentRepository.delete(appointment);
		
	}



}
