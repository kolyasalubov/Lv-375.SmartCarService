package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.stoDto.FaultCodeDto;

public interface FaultCodeService {

	String getFaultCodeDescription (String faultCode);
	
	FaultCodeDto getFaultCode (String faultCode);
	
	FaultCodeDto getFaultCodeDto (long id);
}
