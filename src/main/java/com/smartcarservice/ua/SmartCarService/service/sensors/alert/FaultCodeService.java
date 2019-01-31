package com.smartcarservice.ua.SmartCarService.service.sensors.alert;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;

public interface FaultCodeService {

	String getFaultCodeDescription (String faultCode);
	
	FaultCode getFaultCode (String faultCode);
}
