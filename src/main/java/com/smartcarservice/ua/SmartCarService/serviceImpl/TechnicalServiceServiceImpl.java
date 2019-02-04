package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.TechnicalServiceDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import com.smartcarservice.ua.SmartCarService.repository.TechnicalServiceRepository;
import com.smartcarservice.ua.SmartCarService.service.TechnicalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnicalServiceServiceImpl implements TechnicalServiceService {

    @Autowired
    TechnicalServiceRepository repository;

    private TechnicalServiceDto convertToDto(TechnicalService technicalService){
        TechnicalServiceDto dto = new TechnicalServiceDto();

        dto.setStoId(technicalService.getStoId());
        dto.setName(technicalService.getName());
        dto.setAddress(technicalService.getAddress());
        dto.setDealer(technicalService.getDealer());
        dto.setTechnicalManager(technicalService.getTechnicalManager());
        dto.setWorkerSet(technicalService.getWorkers());

        return dto;
    }

    @Override
    public List<TechnicalService> getAllTechnicalServices() {
        return repository.findAll();
    }

    @Override
    public List<TechnicalServiceDto> getAllTechnicalServicesDto() {
        List<TechnicalService> technicalServiceList = repository.findAll();
        List<TechnicalServiceDto> technicalServiceDtoList = new ArrayList<>();

        for(TechnicalService eachTechnicalService: technicalServiceList){
            technicalServiceDtoList.add(convertToDto(eachTechnicalService));
        }
        return technicalServiceDtoList;
    }

    @Override
    public TechnicalService getByTechnicalManager(TechnicalManager technicalManager) {
        return repository.getByTechnicalManager(technicalManager);
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
