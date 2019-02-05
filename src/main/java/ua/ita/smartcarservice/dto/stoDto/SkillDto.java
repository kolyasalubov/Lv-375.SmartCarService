package ua.ita.smartcarservice.dto.stoDto;

import ua.ita.smartcarservice.entity.sensors.alert.FaultCode;
import lombok.Data;

import java.util.List;

@Data
public class SkillDto {

    private Long id;

    private String name;

    private Long requiredTime;

    private List<FaultCode> faultCode;
}
