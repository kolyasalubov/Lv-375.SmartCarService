package com.smartcarservice.ua.SmartCarService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.FaultCodeDto;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;
import com.smartcarservice.ua.SmartCarService.repository.FaultCodeRepository;
import com.smartcarservice.ua.SmartCarService.service.FaultCodeService;

@Service
public class FaultCodeImpl implements FaultCodeService {

	@Autowired
	private FaultCodeRepository faultCodeRepository;
	
//	@Override
//	public String getFaultCodeDescription(String faultCode) {
//		return faultCodeRepository.getFaultCodeDescription(faultCode);
//	}

	@Override
	public FaultCodeDto getFaultCode(String code) {
		return entityToDto(faultCodeRepository.getFaultCode(code));
	}
	
	private FaultCodeDto entityToDto(FaultCode entity) {
		return new FaultCodeDto(entity.getId(), 
							   entity.getFaultCode(), 
							   entity.getDescription(), 
							   entity.getType(),
							   entity.getSkill());
	}
}
