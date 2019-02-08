package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.stoDto.TechnicalManagerDto;
import ua.ita.smartcarservice.entity.sto.TechnicalManager;

public interface TechnicalManagerService {
    void createTechnicalManager(TechnicalManager technicalManager);
    void updateTechnicalManager(TechnicalManager technicalManager);
    void updateTechnicalManager(TechnicalManagerDto technicalManagerDto);
    void deleteTechnicalManager(Long id);
    TechnicalManagerDto getTechnicalManagerDto(Long id);
    TechnicalManager getTechnicalManager(Long id);

}
