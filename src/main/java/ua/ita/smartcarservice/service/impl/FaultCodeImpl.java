package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.ita.smartcarservice.dto.stoDto.FaultCodeDto;
import ua.ita.smartcarservice.entity.sensors.alert.FaultCode;
import ua.ita.smartcarservice.repository.FaultCodeRepository;
import ua.ita.smartcarservice.service.FaultCodeService;

@Service
public class FaultCodeImpl implements FaultCodeService {

	@Autowired
	private FaultCodeRepository faultCodeRepository;
	
	@Override
	public String getFaultCodeDescription(String faultCode) {
		return faultCodeRepository.getFaultCodeDescription(faultCode);
	}

	@Override
	public FaultCodeDto getFaultCode(String code) {
		return new FaultCodeDto (faultCodeRepository.getFaultCode(code));
	}

	@Override
	public FaultCodeDto getFaultCodeDto(long id) {
		FaultCode entity = faultCodeRepository.getOne(id);
		FaultCodeDto dto = new FaultCodeDto(entity.getId(), 
							   entity.getFaultCode(), 
							   entity.getDescription(), 
							   entity.getType(),
							   entity.getSkill());
		return dto;
	}


}
