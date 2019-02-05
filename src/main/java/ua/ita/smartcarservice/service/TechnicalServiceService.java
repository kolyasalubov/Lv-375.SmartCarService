package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.stoDto.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;

import java.util.List;

public interface TechnicalServiceService {
    TechnicalService getTechnicalServiceById(Long id);
    void createTechnicalService(String name, String address);
    List<TechnicalService> getAllTechnicalServices();
    List<TechnicalServiceDto> getAllTechnicalServicesDto();
    TechnicalService getByTechnicalManager(TechnicalManager technicalManager);
    TechnicalServiceDto getTechnicalServiceDtoById(Long id);
    TechnicalServiceDto updateTechnicalService(TechnicalService technicalService);
    TechnicalServiceDto updateTechnicalService(TechnicalServiceDto technicalServiceDto);
}
