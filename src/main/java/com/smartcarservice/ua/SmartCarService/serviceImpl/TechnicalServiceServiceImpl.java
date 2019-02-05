package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.TechnicalServiceDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import com.smartcarservice.ua.SmartCarService.repository.DealerRepository;
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

    @Autowired
    DealerRepository dealerRepository;

    @Override
    public TechnicalServiceDto updateTechnicalService(TechnicalService technicalService) {
        repository.save(technicalService);
        return convertToDto(repository.getOne(technicalService.getStoId()));
    }

    @Override
    public TechnicalServiceDto updateTechnicalService(TechnicalServiceDto technicalServiceDto) {
        TechnicalService technicalService = convertToEntity(technicalServiceDto);

        return updateTechnicalService(technicalService);
    }

    @Override
    public TechnicalServiceDto getTechnicalServiceDtoById(Long id) {
        return convertToDto(getTechnicalServiceById(id));
    }

    public TechnicalServiceDto convertToDto(TechnicalService technicalService){
        TechnicalServiceDto dto = new TechnicalServiceDto();

        dto.setStoId(technicalService.getStoId());
        dto.setName(technicalService.getName());
        dto.setAddress(technicalService.getAddress());
        dto.setDealer(technicalService.getDealer());
        dto.setTechnicalManager(technicalService.getTechnicalManager());
        dto.setWorkerSet(technicalService.getWorkers());

        return dto;
    }

    public TechnicalService convertToEntity(TechnicalServiceDto technicalServiceDto){
        TechnicalService entity = new TechnicalService();

        entity.setStoId(technicalServiceDto.getStoId());
        entity.setName(technicalServiceDto.getName());
        entity.setAddress(technicalServiceDto.getAddress());
        entity.setDealer(technicalServiceDto.getDealer());
        entity.setTechnicalManager(technicalServiceDto.getTechnicalManager());
        entity.setWorkers(technicalServiceDto.getWorkerSet());

        return entity;
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
    public void createTechnicalServiceByDealer(String name, String address, Long id) {
        TechnicalService technicalService=new TechnicalService();

        technicalService.setName(name);
        technicalService.setAddress(address);
        technicalService.setDealer(dealerRepository.getDealerById(id));

    }

    @Override
    public TechnicalService getTechnicalServiceById(Long id) {
        return repository.getOne(id);
    }
}
