package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;
import lombok.Data;

@Data
public class SkillDto {

    private String name;

    private FaultCode faultCode;

}
