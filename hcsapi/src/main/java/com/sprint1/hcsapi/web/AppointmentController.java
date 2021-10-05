package com.sprint1.hcsapi.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.hcsapi.domain.Appointment;
import com.sprint1.hcsapi.service.AppointmentService;
import com.sprint1.hcsapi.service.MapValidationErrorService;

/**
 * This class is used for handling requests and generates json response
 */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	/**
	 * create a response entity for createAppointment method  with appointment details and http status created
	 */
	@PostMapping("/create/{dcId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> createAppointment(
			@RequestHeader Map<String,String> header,
			@Valid @RequestBody Appointment appointment,BindingResult result,
			@PathVariable long dcId)
	{
		ResponseEntity<?> errorMap=mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		String token = header.get("authorization").substring(7);
		Appointment savedAppointment= appointmentService.save(token,appointment,dcId);
		return new ResponseEntity<Appointment>(savedAppointment, HttpStatus.CREATED);
	}
	
	/**
	 * create a response entity for updateAppointment method  with appointment details and http status created
	 */
	@PostMapping("/validate")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> validateAppointment(@Valid @RequestBody Appointment appointment,BindingResult result){
		ResponseEntity<?> errorMap=mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		Appointment validatedAppointment= appointmentService.validate(appointment);
		return new ResponseEntity<Appointment>(validatedAppointment, HttpStatus.OK);
	}
	
	/**
	 * create a response entity for getAppointmentById method  with path variable appointment id and http status ok
	 */
	@GetMapping("/{appointmentId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<?> getAppointmentById(@PathVariable long appointmentId){
		Appointment appointment=appointmentService.viewAppointmentById(appointmentId);
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
	
	/**
	 * this method is used to return the list of appointments to the admin
	 */
	@GetMapping("/allAppointments")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Iterable<Appointment> getAllAppointments(){
		return appointmentService.viewAllAppointments();
	}
	
	/**
	 * create a response entity for deleteAppointment method  with appointment id and http status ok
	 */
	@DeleteMapping("/{appointmentId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteAppointment(@PathVariable long appointmentId){
		appointmentService.removeAppointmentById(appointmentId);
		return new ResponseEntity<String>("Appointment with id : "+appointmentId+" is deleted", HttpStatus.OK);
	}
	
}
