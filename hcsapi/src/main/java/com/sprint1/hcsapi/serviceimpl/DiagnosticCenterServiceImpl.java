package com.sprint1.hcsapi.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.hcsapi.domain.DiagnosticCenter;
import com.sprint1.hcsapi.exception.DiagnosticCenterException;
import com.sprint1.hcsapi.repository.DiagnosticCenterRepository;
import com.sprint1.hcsapi.service.DiagnosticCenterService;

/*
 * This class is to implement all methods from service layer related to Diagnostic Center
 * All business Logic related for Diagnostic Center is written in methods of this class
 */
@Service
public class DiagnosticCenterServiceImpl implements DiagnosticCenterService {

	
	@Autowired
	private DiagnosticCenterRepository diagnosticCenterRepository;
	
	/*
	 * This method will create or update Diagnostic Center
	 * we will get Diagnostic Center from controller via parameter
	 * if dcID is not greater than 0 which means this is new Diagnostic Center
	 * this method will save it
	 * or else it will get the Diagnostic Center with same id and update it 
	 * and return the Diagnostic Center we have saved/updated
	 * 
	 */
	@Override
	public DiagnosticCenter saveOrUpdate(DiagnosticCenter dc){
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

	/*
	 * This method will find a Diagnostic Center through its dcID
	 * and return a Diagnostic Center with dcID given in parameter 
	 */
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

	/*
	 * This method will return all Diagnostic Centers 
	 */
	@Override
	public Iterable<DiagnosticCenter> findAll() {
		return diagnosticCenterRepository.findAll();
	}

	/*
	 * This method will delete the Diagnostic Center via its dcID
	 */
	@Override
	public void deleteDiagnosticCenterBydcId(Long dcID) {
		DiagnosticCenter dc = findDiagnosticCenter(dcID);
		diagnosticCenterRepository.delete(dc);

	}

}
