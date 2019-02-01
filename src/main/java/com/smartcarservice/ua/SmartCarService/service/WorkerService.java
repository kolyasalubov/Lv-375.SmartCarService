package com.smartcarservice.ua.SmartCarService.service;


import com.smartcarservice.ua.SmartCarService.dto.StoDto.WorkerDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Worker;

import java.util.List;

public interface WorkerService {

    List<WorkerDto> findAllWorkersBySkill(String name);

}
