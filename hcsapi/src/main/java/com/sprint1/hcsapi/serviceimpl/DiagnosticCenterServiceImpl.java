package com.sprint1.hcsapi.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.hcsapi.domain.DiagnosticCenter;
import com.sprint1.hcsapi.exception.DiagnosticCenterException;
import com.sprint1.hcsapi.repository.DiagnosticCenterRepository;
import com.sprint1.hcsapi.service.DiagnosticCenterService;


@Service
public class DiagnosticCenterServiceImpl implements DiagnosticCenterService {

	
	@Autowired
	private DiagnosticCenterRepository diagnosticCenterRepository;
	
	
	@Override
	public DiagnosticCenter saveOrUpdate(DiagnosticCenter dc){
		// this method save the new d c
			try {
				if(dc.getDcID() >= 0)
				{
					DiagnosticCenter dctemp = findDiagnosticCenter(dc.getDcID());
					dctemp = dc;
					return diagnosticCenterRepository.save(dctemp);
				}
				else{
					return diagnosticCenterRepository.save(dc);
				}
			} catch (Exception e) {
			
			}

			return dc;
		
	}

	@Override
	public DiagnosticCenter findDiagnosticCenter(Long dcID) {
		DiagnosticCenter dc = diagnosticCenterRepository.findDiagnosticCenterBydcID(dcID);
		try {
			if(dc == null)
				throw new DiagnosticCenterException("No such project found");
		}catch(Exception e)
		{
			
		}
		return dc;
	}

	@Override
	public Iterable<DiagnosticCenter> findAll() {
		return diagnosticCenterRepository.findAll();
	}

	@Override
	public void deleteDiagnosticCenterBydcId(Long dcID) {
		DiagnosticCenter dc = findDiagnosticCenter(dcID);
		diagnosticCenterRepository.delete(dc);

	}

}
