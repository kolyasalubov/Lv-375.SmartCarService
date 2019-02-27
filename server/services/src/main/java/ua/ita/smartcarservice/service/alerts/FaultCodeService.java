package ua.ita.smartcarservice.service.alerts;

import ua.ita.smartcarservice.dto.alerts.FaultCodeDto;

public interface FaultCodeService {

	/* READ */
	FaultCodeDto getFaultCode (String faultCode);

}
