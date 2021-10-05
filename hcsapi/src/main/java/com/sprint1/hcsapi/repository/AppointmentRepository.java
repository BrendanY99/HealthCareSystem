package com.sprint1.hcsapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.hcsapi.domain.Appointment;

/**
 * This interface is used to perform Crud operations for Appointment Repository  
 */
@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
	
}
