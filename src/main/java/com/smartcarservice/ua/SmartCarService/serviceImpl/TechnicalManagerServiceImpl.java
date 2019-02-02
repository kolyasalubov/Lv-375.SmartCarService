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
    }

    @Override
    public void deleteTechnicalManager(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TechnicalManagerDto getTechnicalManager(Long id) {
        TechnicalManager temp;
        TechnicalManagerDto result;

        temp = (TechnicalManager) repository.getTechnicalManagerById(id);
        result = new TechnicalManagerDto(temp.getId(), temp.getEmail(), temp.getPassword(), temp.getFullName(), temp.getUserName(), temp.getTechnicalService(), temp.getWorkers());

        return result;
    }
}
