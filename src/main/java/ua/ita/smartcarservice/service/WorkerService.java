package ua.ita.smartcarservice.service;


import ua.ita.smartcarservice.dto.stoDto.WorkerDto;
import ua.ita.smartcarservice.entity.sto.Skill;
import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;

import java.util.List;

public interface WorkerService {

    List<WorkerDto> findAllWorkersBySkillAndSto(String name, Long stoId);
    List<WorkerDto> findWorkersByTechnicalManager(TechnicalManager technicalManager);
    void saveWorker(String fullName, Skill skill, TechnicalManager technicalManager, TechnicalService technicalService);

}
