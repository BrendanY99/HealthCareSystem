package com.sprint1.hcsapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.hcsapi.domain.DiagnosticCenter;

@Repository
public interface DiagnosticCenterRepository extends  CrudRepository<DiagnosticCenter, Long> {
	
	//no need to implement methods as all are present in crud repository
	/*
	 * This method will  return  Diagnostic Center by its dcID(primary key)
	 * give dcID as parameter to method
	 */
	DiagnosticCenter findDiagnosticCenterBydcID(Long dcID);
	

}