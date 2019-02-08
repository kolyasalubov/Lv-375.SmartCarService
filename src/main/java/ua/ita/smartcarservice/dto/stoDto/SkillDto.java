package ua.ita.smartcarservice.dto.stoDto;

import java.util.List;

import ua.ita.smartcarservice.entity.sensors.alert.FaultCode;
import lombok.Data;

@Data
public class SkillDto {

    private Long id;

    private String name;

    public Long getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(Long requiredTime) {
        this.requiredTime = requiredTime;
    }

    private Long requiredTime;

    private List<FaultCode> faultCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public List<FaultCode> getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(List<FaultCode> faultCode) {
		this.faultCode = faultCode;
	}

    
}
