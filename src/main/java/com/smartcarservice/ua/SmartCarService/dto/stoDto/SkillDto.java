package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.FaultCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class SkillDto {

    private Long id;

    private String name;

    private Long requiredTime;

    private List<FaultCode> faultCode;
}
