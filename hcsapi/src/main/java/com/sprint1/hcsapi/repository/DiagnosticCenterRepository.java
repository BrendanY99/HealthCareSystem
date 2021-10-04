package com.sprint1.hcsapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.hcsapi.domain.DiagnosticCenter;

@Repository
public interface DiagnosticCenterRepository extends  CrudRepository<DiagnosticCenter, Long> {
	
	//no need to implement methods as all are present in crud repository
	
	DiagnosticCenter findDiagnosticCenterBydcID(Long dcID);
	

}