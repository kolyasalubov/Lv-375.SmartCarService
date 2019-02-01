package com.smartcarservice.ua.SmartCarService.service;


import com.smartcarservice.ua.SmartCarService.dto.stoDto.WorkerDto;

import java.util.List;

public interface WorkerService {

    List<WorkerDto> findAllWorkersBySkill(String name);

}
