package com.smartcarservice.ua.SmartCarService.entity.sensors.alert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import lombok.Data;

@Data
@Entity
@Table(name = "fault_code")
public class FaultCode {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column (length = 10, name = "fault_code", nullable = false)
	private String faultCode;
	
	@Column (length = 250, name = "description", nullable = false)
	private String description;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
	private Skill skill;
}
