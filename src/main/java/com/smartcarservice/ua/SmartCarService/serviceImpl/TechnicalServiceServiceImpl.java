package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.TechnicalServiceDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import com.smartcarservice.ua.SmartCarService.repository.TechnicalServiceRepository;
import com.smartcarservice.ua.SmartCarService.service.TechnicalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalServiceServiceImpl implements TechnicalServiceService {

    @Autowired
    TechnicalServiceRepository repository;

    @Override
    public List<TechnicalService> getAllTechnicalServices() {
        return repository.findAll();
    }

    @Override
    public void createTechnicalService(String name, String address) {
        TechnicalService technicalService = new TechnicalService();

        technicalService.setName(name);
        technicalService.setAddress(address);

        repository.save(technicalService);
    }

    @Override
    public TechnicalService getTechnicalServiceById(Long id) {
        return repository.getOne(id);
    }
}
