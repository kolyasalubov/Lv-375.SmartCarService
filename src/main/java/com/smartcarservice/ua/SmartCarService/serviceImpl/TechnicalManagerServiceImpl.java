package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.TechnicalManagerDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.repository.TechnicalManagerRepository;
import com.smartcarservice.ua.SmartCarService.service.TechnicalManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicalManagerServiceImpl implements TechnicalManagerService {

    @Autowired
    TechnicalManagerRepository repository;

    @Override
    public void createTechnicalManager(TechnicalManager technicalManager) {

        repository.save(technicalManager);
    }

    @Override
    public void updateTechnicalManager(Long id, TechnicalManager technicalManager) {
        repository.save(technicalManager);
    }

    @Override
    public void updateTechnicalManager(Long id, TechnicalManagerDto technicalManagerDto) {
        TechnicalManager technicalManager = new TechnicalManager();

        technicalManager.setId(id);
        technicalManager.setTechnicalService(technicalManagerDto.getTechnicalService());
        technicalManager.setWorkers(technicalManagerDto.getWorkers());
        technicalManager.setEmail(technicalManagerDto.getEmail());
        technicalManager.setFullName(technicalManagerDto.getFullName());
        technicalManager.setUserName(technicalManagerDto.getUserName());
        technicalManager.setPassword(technicalManagerDto.getPassword());

        repository.save(technicalManager);
    }

    @Override
    public void deleteTechnicalManager(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TechnicalManagerDto getTechnicalManagerDto(Long id) {
        TechnicalManager temp;
        TechnicalManagerDto managerDto;

        temp = (TechnicalManager) repository.getTechnicalManagerById(id);
        managerDto = new TechnicalManagerDto(temp.getId(), temp.getEmail(), temp.getPassword(), temp.getFullName(), temp.getUserName(), temp.getTechnicalService(), temp.getWorkers());

        return managerDto;
    }

    @Override
    public TechnicalManager getTechnicalManager(Long id) {
        return repository.getTechnicalManagerById(id);
    }
}
