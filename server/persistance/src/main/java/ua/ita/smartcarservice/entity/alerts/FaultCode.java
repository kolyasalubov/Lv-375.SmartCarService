package ua.ita.smartcarservice.entity.alerts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fault_code")
public class FaultCode {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column (length = 25, name = "fault_code", nullable = false, unique = true)
	private String faultCode;
	
	@Column (length = 250, name = "description", nullable = false)
	private String description;

	@Column (length = 1050, name = "suggestion")
	private String suggestion;

	@Column (length = 100, name = "type", nullable = false)
	private String type;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
	private SkillEntity skill;

	public FaultCode(String faultCode, String description, String type, SkillEntity skill) {
		this.faultCode = faultCode;
		this.description = description;
		this.type = type;
		this.skill = skill;
	}

	/* Constructor for warning lights that do not requires service help */
	public FaultCode(String faultCode, String description, String suggestion, String type) {
		this.faultCode = faultCode;
		this.description = description;
		this.suggestion = suggestion;
		this.type = type;
	}


}
