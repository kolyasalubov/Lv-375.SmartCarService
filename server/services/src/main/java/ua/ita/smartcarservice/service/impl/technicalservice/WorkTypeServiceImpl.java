package ua.ita.smartcarservice.service.impl.technicalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.WorkTypeDto;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.repository.technicalservice.WorkTypeRepository;
import ua.ita.smartcarservice.service.technicalservice.SkillService;
import ua.ita.smartcarservice.service.technicalservice.WorkTypeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Autowired
    private SkillService skillService;

    @Override
    public Map<String, WorkType> findDistinctWorkByName(){
        Map<String, WorkType> requiredTimeByName = new HashMap<>();
        workTypeRepository.findAll().forEach(workType -> requiredTimeByName.put(workType.getName(), workType));
        return requiredTimeByName;
    }

    @Override
    public Map<String, List<WorkTypeDto>> getAllService(Long id){
        Map<String, List<WorkTypeDto>> allService = new HashMap <>();
        skillService.findSkillNameByCarId(id).forEach(skillDto -> {
            List<WorkTypeDto> workTypeDtos = new ArrayList <>();
            workTypeRepository.findAllWorkBySkill(skillDto.getName()).forEach(workType -> {
                workTypeDtos.add(getDto(workType));
            });
            allService.put(skillDto.getName(), workTypeDtos);
        });
        return allService;
    }

    private WorkTypeDto getDto(WorkType workType){
        WorkTypeDto workTypeDto = new WorkTypeDto();
        workTypeDto.setName(workType.getName());
        workTypeDto.setCost(workType.getCost());
        workTypeDto.setRequiredTime(workType.getRequiredTime());
        workTypeDto.setSkillName(workType.getSkill().getName());

        return workTypeDto;
    }

}
