package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.WorkerDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.Skill;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.Worker;
import com.smartcarservice.ua.SmartCarService.repository.WorkerRepository;
import com.smartcarservice.ua.SmartCarService.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Override
    public List<WorkerDto> findAllWorkersBySkill(String name) {
        List <WorkerDto> workerDtos = new ArrayList <>();
        for(Worker worker : workerRepository.findAllWorkersBySkill(name)){
            workerDtos.add(getWorkerDto(worker));
        }
        return workerDtos;
    }

    private WorkerDto getWorkerDto(Worker worker){
        WorkerDto workerDto = new WorkerDto();
        workerDto.setFullName(worker.getFullName());
        return workerDto;
    }

    @Override
    public List<WorkerDto> findWorkersByTechnicalManager(TechnicalManager technicalManager) {
        List<Worker> workersList = workerRepository.findWorkersByTechnicalManager(technicalManager);
        List<WorkerDto> workerDtoList = new ArrayList<>();

        for(Worker eachWorker: workersList){
            WorkerDto workerDto = new WorkerDto();

            workerDto.setFullName(eachWorker.getFullName());
            workerDto.setSkill(eachWorker.getSkill());

            workerDtoList.add(workerDto);
        }

        return workerDtoList;
    }

    @Override
    public void saveWorker(String fullName, Skill skill, TechnicalManager technicalManager) {

        Worker worker = new Worker();
        worker.setFullName(fullName);
        worker.setSkill(skill);
        worker.setTechnicalManager(technicalManager);

        workerRepository.save(worker);
    }
}
