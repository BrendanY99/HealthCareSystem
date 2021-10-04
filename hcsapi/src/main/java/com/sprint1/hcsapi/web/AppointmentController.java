package com.sprint1.hcsapi.web;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.hcsapi.domain.Appointment;
import com.sprint1.hcsapi.service.AppointmentService;
import com.sprint1.hcsapi.service.MapValidationErrorService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> createAppointment(@Valid @RequestBody Appointment appointment,BindingResult result){
		ResponseEntity<?> errorMap=mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		Appointment savedAppointment= appointmentService.save(appointment);
		return new ResponseEntity<Appointment>(savedAppointment, HttpStatus.CREATED);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateAppointment(@Valid @RequestBody Appointment appointment,BindingResult result){
		ResponseEntity<?> errorMap=mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		Appointment savedAppointment= appointmentService.update(appointment);
		return new ResponseEntity<Appointment>(savedAppointment, HttpStatus.OK);
	}
	
	@GetMapping("/{appointmentId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<?> getAppointmentById(@PathVariable long appointmentId){
		Appointment appointment=appointmentService.viewAppointmentById(appointmentId);
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
	
	@GetMapping("/allAppointments")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Iterable<Appointment> getAllAppointments(){
		return appointmentService.viewAllAppointments();
	}
	
	@DeleteMapping("/{appointmentId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteAppointment(@PathVariable long appointmentId){
		appointmentService.removeAppointmentById(appointmentId);
		return new ResponseEntity<String>("Appointment with id : "+appointmentId+" is deleted", HttpStatus.OK);
	}
	
}
