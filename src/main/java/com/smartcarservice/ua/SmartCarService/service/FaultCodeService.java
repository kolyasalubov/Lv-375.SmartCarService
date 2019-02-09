package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.FaultCodeDto;

public interface FaultCodeService {

//	String getFaultCodeDescription (String faultCode);
	
	FaultCodeDto getFaultCode (String faultCode);
}
