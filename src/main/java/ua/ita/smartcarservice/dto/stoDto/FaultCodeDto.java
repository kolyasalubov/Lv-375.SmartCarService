package ua.ita.smartcarservice.dto.stoDto;



import ua.ita.smartcarservice.entity.sensors.alert.FaultCode;
import ua.ita.smartcarservice.entity.sto.Skill;

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
	
	public FaultCodeDto (FaultCode faultCode) {
		this.id = faultCode.getId();
		this.faultCode = faultCode.getFaultCode();
		this.description = faultCode.getDescription();
		this.type = faultCode.getType();
		this.skill= faultCode.getSkill();
	}
	 
	 
}
