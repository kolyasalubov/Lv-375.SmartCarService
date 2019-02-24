package ua.ita.smartcarservice.service.impl.technicalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.repository.technicalservice.WorkTypeRepository;
import ua.ita.smartcarservice.service.technicalservice.WorkTypeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Override
    public Map<String, WorkType> findDistinctWorkByName(){
        Map<String, WorkType> requiredTimeByName = new HashMap<>();
        workTypeRepository.findAll().forEach(workType -> requiredTimeByName.put(workType.getName(), workType));
        return requiredTimeByName;
    }

}
