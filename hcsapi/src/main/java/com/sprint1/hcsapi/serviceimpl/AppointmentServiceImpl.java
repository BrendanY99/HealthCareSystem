package com.sprint1.hcsapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.hcsapi.configuration.JwtTokenProvider;
import com.sprint1.hcsapi.domain.Appointment;
import com.sprint1.hcsapi.domain.DiagnosticTest;
import com.sprint1.hcsapi.exception.AppointmentIDException;
import com.sprint1.hcsapi.repository.AppointmentRepository;
import com.sprint1.hcsapi.repository.DiagnosticCenterRepository;
import com.sprint1.hcsapi.repository.DiagnosticTestRepository;
import com.sprint1.hcsapi.repository.UserRepository;
import com.sprint1.hcsapi.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DiagnosticCenterRepository diagnosticCenterRepository;
	
	@Autowired
	private DiagnosticTestRepository diagnosticTestRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	public Appointment save(String token,Appointment appointment,long dcId) {

			appointment.setUser(userRepository.findByUsername(jwtTokenProvider.getUsername(token)));
			appointment.setDiagnosticCenter(diagnosticCenterRepository.findById(dcId).get());
			appointment.setDiagnosticTest(diagnosticTestRepository.findByTestName(appointment.getTestName()));
			return appointmentRepository.save(appointment);

	}
	
	@Override
	public Appointment update(Appointment appointment,long testId) {
		try {
			
			System.out.println(appointment.getDiagnosticTest());
			
			Appointment oldAppointment=appointmentRepository.findById(appointment.getId()).get();
			
			
			if(appointment.getDate()!=null) {
				oldAppointment.setDate(appointment.getDate());
			}
			
			if(appointment.getTestName()!=null) {
				oldAppointment.setTestName(appointment.getTestName());
			}
			
			String testStatus=diagnosticTestRepository.findById(testId).get().getTestStatus();
			
			if(testStatus.equals("Available")) {
				oldAppointment.setApprovalStatus("Accepted");
				DiagnosticTest diagnosticTest=diagnosticTestRepository.findById(testId).get();
				diagnosticTest.setTestStatus("Not Available");
				diagnosticTestRepository.save(diagnosticTest);
			}
			else {
				oldAppointment.setApprovalStatus("Rejected");
			}
			return appointmentRepository.save(oldAppointment);
			

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
