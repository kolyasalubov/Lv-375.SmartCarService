package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.repository.booking.WorkTimeRepository;
import ua.ita.smartcarservice.service.booking.WorkTimeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkTimeServiceImpl implements WorkTimeService {

    @Autowired
    WorkTimeRepository workTimeRepository;

    public Map<String, List<WorkType>> findWorkerTasksByReportId(long reportId) {

        List<Object[]> workerTasksArray = workTimeRepository.findWorkerTasksByReportId(reportId);

        Map<String, List<WorkType>> workerTasks = new HashMap<>();

        workerTasksArray.forEach(element -> {
            String workerFullName = element[0].toString();
            List<WorkType> workTypes = workerTasks.containsKey(workerFullName)
                    ? workerTasks.get(workerFullName) : new ArrayList<>();

            workTypes.add((WorkType) element[1]);
            workerTasks.put(workerFullName, workTypes);
        });

        return workerTasks;
    }
}

