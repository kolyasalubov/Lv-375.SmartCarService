package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WorkerBySkillNameDto {

    Map<String, List<WorkerDto>> workerList;

    int requiredTime;

}
