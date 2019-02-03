package com.smartcarservice.ua.SmartCarService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;
import com.smartcarservice.ua.SmartCarService.repository.FaultCodeRepository;
import com.smartcarservice.ua.SmartCarService.service.FaultCodeService;

@Service
public class FaultCodeImpl implements FaultCodeService {

	@Autowired
	private FaultCodeRepository faultCodeRepository;
	
	@Override
	public String getFaultCodeDescription(String faultCode) {
		return faultCodeRepository.getFaultCodeDescription(faultCode);
	}

	@Override
	public FaultCode getFaultCode(String faultCode) {
		return faultCodeRepository.getFaultCode(faultCode);
	}

}
