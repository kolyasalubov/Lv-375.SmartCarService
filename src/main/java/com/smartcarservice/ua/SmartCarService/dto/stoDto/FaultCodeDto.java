package com.smartcarservice.ua.SmartCarService.dto.stoDto;



import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;

import lombok.Data;

@Data
public class FaultCodeDto{

	 private long id;
	 private String faultCode;
	 private String description;
	 private String type;
	 private Skill skill;
	 
	public FaultCodeDto(long id, String faultCode, String description, String type, Skill skill) {
		this.id = id;
		this.faultCode = faultCode;
		this.description = description;
		this.type = type;
		this.skill = skill;
	}
}
