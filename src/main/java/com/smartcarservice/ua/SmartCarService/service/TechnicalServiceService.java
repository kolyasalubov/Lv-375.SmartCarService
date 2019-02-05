package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.TechnicalServiceDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;

import java.util.List;

public interface TechnicalServiceService {
    TechnicalService getTechnicalServiceById(Long id);
    void createTechnicalService(String name, String address);
    void createTechnicalServiceByDealer(String name, String address,Long id);
    List<TechnicalService> getAllTechnicalServices();
    List<TechnicalServiceDto> getAllTechnicalServicesDto();
    TechnicalService getByTechnicalManager(TechnicalManager technicalManager);
    TechnicalServiceDto getTechnicalServiceDtoById(Long id);
    TechnicalServiceDto updateTechnicalService(TechnicalService technicalService);
    TechnicalServiceDto updateTechnicalService(TechnicalServiceDto technicalServiceDto);
}
