package ua.ita.smartcarservice.service.booking;

import ua.ita.smartcarservice.entity.booking.WorkDependency;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import java.util.List;
import java.util.Map;

public interface WorkDependencyService {

    List<WorkDependency> findAll();

    int findRequiredTime(List <String> skillName);

    int findGraphSize();

    Map<String, WorkType> getDistinctSkillByName();

}
