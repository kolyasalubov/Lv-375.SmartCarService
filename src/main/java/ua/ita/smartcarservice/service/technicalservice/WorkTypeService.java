package ua.ita.smartcarservice.service.technicalservice;

import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import java.util.Map;

public interface WorkTypeService {
    Map<String, WorkType> findDistinctWorkByName();
}
