package com.smartcarservice.ua.SmartCarService.service;


import com.smartcarservice.ua.SmartCarService.dto.stoDto.WorkerDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import com.smartcarservice.ua.SmartCarService.entity.sto.Worker;

import java.util.List;

public interface WorkerService {

    List<WorkerDto> findAllWorkersBySkillAndSto(String name, Long stoId);
    List<WorkerDto> findWorkersByTechnicalManager(TechnicalManager technicalManager);
    void saveWorker(String fullName, Skill skill, TechnicalManager technicalManager, TechnicalService technicalService);
    void deleteWorker(TechnicalManager technicalManager, Long workerId) throws Exception;
}
