package ua.ita.smartcarservice.dto.booking;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import java.util.List;

@Data
public class WorkerTasksDto {

    private UserEntity worker;

    private List<WorkType> workTypes;
}
