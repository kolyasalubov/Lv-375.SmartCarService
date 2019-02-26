package ua.ita.smartcarservice.dto.alerts;

import lombok.Data;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

@Data
public class FaultCodeDto{

	private long id;
	private String faultCode;
	private String description;
	private String type;
	private String suggestion;
	private SkillEntity skill;

	public FaultCodeDto(long id, String faultCode, String description, String type, SkillEntity skill) {
		this.id = id;
		this.faultCode = faultCode;
		this.description = description;
		this.type = type;
		this.skill = skill;
	}

	/* Constructor for warning lights that requires service help */
	public FaultCodeDto(String faultCode, String description, String suggestion, String type, SkillEntity skill) {
		this.faultCode = faultCode;
		this.description = description;
		this.suggestion = suggestion;
		this.type = type;
		this.skill = skill;
	}

	/* Constructor for warning lights that do not requires service help */
	public FaultCodeDto(String faultCode, String description, String suggestion, String type) {
		this.faultCode = faultCode;
		this.description = description;
		this.suggestion = suggestion;
		this.type = type;
	}
}
