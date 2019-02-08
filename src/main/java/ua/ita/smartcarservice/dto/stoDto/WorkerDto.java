package ua.ita.smartcarservice.dto.stoDto;

import ua.ita.smartcarservice.entity.sto.Skill;
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
