package com.sprint1.hcsapi.service;


import com.sprint1.hcsapi.domain.DiagnosticCenter;

public interface DiagnosticCenterService {

	public DiagnosticCenter saveOrUpdate(DiagnosticCenter  dc);
	
	public DiagnosticCenter findDiagnosticCenter(Long dcID);

	public Iterable<DiagnosticCenter> findAll();

	public void deleteDiagnosticCenterBydcId(Long dcID);

	
}
