package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class WorkerWithSkillDto {

    private List<String> skillName;
    private List<String> workName;
    private Long searchId;

    public List<String> getName() {
        return skillName;
    }

    public void setSkillName(List<String> skillName) {
        this.skillName = skillName;
    }

    public List<String> getWorkName() {
        return workName;
    }

    public void setWorkName(List<String> workName) {
        this.workName = workName;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }
}
