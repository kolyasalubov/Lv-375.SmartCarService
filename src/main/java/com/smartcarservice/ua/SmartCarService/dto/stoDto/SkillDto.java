package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;
import lombok.Data;

@Data
public class SkillDto {

    private Long id;

    private String name;

    private FaultCode faultCode;

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

    public FaultCode getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(FaultCode faultCode) {
        this.faultCode = faultCode;
    }
}
