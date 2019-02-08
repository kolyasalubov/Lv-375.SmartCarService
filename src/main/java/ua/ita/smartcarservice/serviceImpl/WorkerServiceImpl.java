package ua.ita.smartcarservice.serviceImpl;

import ua.ita.smartcarservice.dto.stoDto.WorkerDto;
import ua.ita.smartcarservice.entity.sto.Skill;
import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
import ua.ita.smartcarservice.entity.sto.Worker;
import ua.ita.smartcarservice.repository.WorkerRepository;
import ua.ita.smartcarservice.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Override
    public List<WorkerDto> findAllWorkersBySkillAndSto(String name, Long stoId) {
        List <WorkerDto> workerDtos = new ArrayList <>();
        for(Worker worker : workerRepository.findAllWorkersBySkillAndSto(name, stoId)){
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
    public void saveWorker(String fullName, Skill skill, TechnicalManager technicalManager,
                           TechnicalService technicalService) {

        Worker worker = new Worker();
        worker.setFullName(fullName);
        worker.setSkill(skill);
        worker.setTechnicalManager(technicalManager);
        worker.setTechnicalService(technicalService);

        workerRepository.save(worker);
    }
}
