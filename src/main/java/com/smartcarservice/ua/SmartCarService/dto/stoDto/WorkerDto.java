package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import lombok.Data;

@Data
public class WorkerDto {

    private Long workerId;
    private String fullName;
    private Skill skill;

}
