package ua.ita.smartcarservice.dto.stoDto;

import ua.ita.smartcarservice.entity.sto.Skill;
import lombok.Data;

@Data
public class WorkerDto {

    private Long workerId;
    private String fullName;
    private Skill skill;

}
