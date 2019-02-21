package ua.ita.smartcarservice.dto.booking;

import lombok.Data;


import java.util.List;
import java.util.Map;

@Data
public class WorkerWithTimeDto {

    Map<String, List<WorkerDto>> workerList;

    int requiredTime;

}
