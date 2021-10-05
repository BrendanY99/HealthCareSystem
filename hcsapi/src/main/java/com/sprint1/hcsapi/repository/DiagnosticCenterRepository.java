package com.sprint1.hcsapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.hcsapi.domain.DiagnosticCenter;

@Repository
public interface DiagnosticCenterRepository extends  CrudRepository<DiagnosticCenter, Long> {
	
	//no need to implement methods as all are present in Crud repository
	/*
	 * This method will  return  Diagnostic Center by its id(primary key)
	 * give id as parameter to method
	 */
	DiagnosticCenter findDiagnosticCenterById(Long id);
	

}