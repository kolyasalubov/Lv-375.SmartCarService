package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.stoDto.TechnicalManagerDto;
import ua.ita.smartcarservice.entity.sto.TechnicalManager;

public interface TechnicalManagerService {
    public TechnicalManager createTechnicalManager(String fullname, String username,
                                                   String email, String password, Long serviceId);
    void createTechnicalManager(TechnicalManager technicalManager);
    public TechnicalManagerDto updateTechnicalManager(Long id, String fullname, String username, String password);
    void updateTechnicalManager(TechnicalManager technicalManager);
    void updateTechnicalManager(TechnicalManagerDto technicalManagerDto);
    void deleteTechnicalManager(Long id);
    TechnicalManagerDto getTechnicalManagerDto(Long id);
    TechnicalManager getTechnicalManager(Long id);

}
