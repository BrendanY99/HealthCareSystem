package com.sprint1.hcsapi.service;

import com.sprint1.hcsapi.domain.DiagnosticCenter;

/**
 * This interface is to declare required methods
 * All methods here will be implemented at another layer
 * All methods contain code related to business logic  
 */
public interface DiagnosticCenterService {

	/**
	 * Method to save or update new Diagnostic Center
	 */
	public DiagnosticCenter saveOrUpdate(DiagnosticCenter diagnosticCenter);
	
	/**
	 * Method to find DiagnosticCenter by its id
	 */
	public DiagnosticCenter findDiagnosticCenter(Long id);

	/**
	 * Method to find all Diagnostic Center
	 */
	public Iterable<DiagnosticCenter> findAll();

	/**
	 * Method to delete DiagnosticCenter by its id
	 */
	public void deleteDiagnosticCenterById(Long id);

	
}
