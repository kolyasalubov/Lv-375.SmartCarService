package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import lombok.Data;

@Data
public class WorkerDto {

    private String fullName;
    private Skill skill;

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
