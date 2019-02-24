package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class WorkerWithSkillDto {

    private List<String> skillName;
    private List<String> workName;
    private Long searchId;

}
