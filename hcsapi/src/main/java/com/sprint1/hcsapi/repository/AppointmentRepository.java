package com.sprint1.hcsapi.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.hcsapi.domain.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
