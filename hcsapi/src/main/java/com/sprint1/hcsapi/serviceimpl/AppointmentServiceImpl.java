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

/**
 * This class is to implement all methods from service layer related to Appointment
 * All business Logic related to Appointment is written in methods of this class
 */
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
	
	/**
	 * This method is overriding the save method of Appointment Service
	 * This method will be used to create a new appointment
	 * Token is used to set the user for that appointment
	 */
	@Override
	public Appointment save(String token,Appointment appointment,long dcId) {

			appointment.setUser(userRepository.findByUsername(jwtTokenProvider.getUsername(token)));
			appointment.setDiagnosticCenter(diagnosticCenterRepository.findById(dcId).get());
			appointment.setDiagnosticTest(diagnosticTestRepository.findByTestName(appointment.getTestName()));
			return appointmentRepository.save(appointment);

	}
	
	/**
	 * This method is overriding the validate method of Appointment Service
	 * This method will also validate if the appointment can be accepted or not based on availability of specific test
	 * If the test is available, then appointment is accepted and Status of test is set to not available
	 */
	@Override
	public Appointment validate(Appointment appointment) {
		try {
			
			Appointment oldAppointment=appointmentRepository.findById(appointment.getId()).get();
			
			String testStatus=diagnosticTestRepository.findByTestName(oldAppointment.getTestName()).getTestStatus();
			
			if(testStatus.equals("Available")) {
				oldAppointment.setApprovalStatus("Accepted");
				DiagnosticTest diagnosticTest=diagnosticTestRepository.findByTestName(oldAppointment.getTestName());
				diagnosticTest.setTestStatus("Not Available");
				diagnosticTestRepository.save(diagnosticTest);
			}
			else {
				oldAppointment.setApprovalStatus("Rejected");
			}
			return appointmentRepository.save(oldAppointment);		

		}
		catch(Exception e) {
			throw new AppointmentIDException("Appointment Id "+appointment.getId()+" does not exists");
		}
	}
	
	/**
	 * This method is overriding the viewAppointmentById method of Appointment Service
	 * This method allows the user and admin to check appointment details by providing the appointment id
	 */
	@Override
	public Appointment viewAppointmentById(long id) {
		Appointment appointment=appointmentRepository.findById(id).get();
		if(appointment==null) {
			throw new AppointmentIDException("Appointment Id "+appointment.getId()+" does not exists");
		}
		return appointment;
	}

	/**
	 * This method is overriding the viewAllAppointments method of Appointment Service
	 * This method is used by Admin to view all appointments
	 */
	@Override
	public Iterable<Appointment> viewAllAppointments() {
		return appointmentRepository.findAll();
	}

	/**
	 * This method is overriding the removeAppointmentById method of Appointment Service
	 * This method is used to remove the appointment by appointment id
	 */
	@Override
	public void removeAppointmentById(long id) {
		try {
			Appointment appointment=appointmentRepository.findById(id).get();
			appointmentRepository.delete(appointment);
		}
		catch(Exception e) {
			throw new AppointmentIDException("Appointment_Id "+id+" does not exists");
		}
		
	}

}
