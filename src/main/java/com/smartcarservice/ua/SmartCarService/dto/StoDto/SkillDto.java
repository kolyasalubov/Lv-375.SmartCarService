package com.smartcarservice.ua.SmartCarService.dto.StoDto;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;
import lombok.Data;

@Data
public class SkillDto {

    private String name;

    private FaultCode faultCode;

}
