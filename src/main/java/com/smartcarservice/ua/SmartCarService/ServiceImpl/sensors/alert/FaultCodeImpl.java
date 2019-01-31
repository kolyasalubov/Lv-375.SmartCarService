package com.smartcarservice.ua.SmartCarService.ServiceImpl.sensors.alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcarservice.ua.SmartCarService.Repository.sensors.alert.FaultCodeRepository;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;
import com.smartcarservice.ua.SmartCarService.service.sensors.alert.FaultCodeService;

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
