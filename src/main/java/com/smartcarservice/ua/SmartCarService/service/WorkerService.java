package com.smartcarservice.ua.SmartCarService.service;


import com.smartcarservice.ua.SmartCarService.dto.stoDto.WorkerDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.Worker;

import java.util.List;

public interface WorkerService {

    List<WorkerDto> findAllWorkersBySkill(String name);
    List<WorkerDto> findWorkersByTechnicalManager(TechnicalManager technicalManager);
    void saveWorker(String fullName, Skill skill, TechnicalManager technicalManager);

}
