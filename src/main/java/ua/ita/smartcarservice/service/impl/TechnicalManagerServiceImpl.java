package ua.ita.smartcarservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.ita.smartcarservice.dto.stoDto.TechnicalManagerDto;
import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
import ua.ita.smartcarservice.repository.TechnicalManagerRepository;
import ua.ita.smartcarservice.service.TechnicalManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.service.TechnicalServiceService;

/**
 * Implementation of the service for TechnicalManager
 */
@Service
public class TechnicalManagerServiceImpl implements TechnicalManagerService {

    @Autowired
    TechnicalManagerRepository repository;

    @Autowired
    TechnicalServiceService technicalServiceService;

    final Logger logger = LoggerFactory.getLogger(TechnicalManagerServiceImpl.class);

    /*
    Method for creating technical manager by parameters and saving it to DB
    */
    public TechnicalManager createTechnicalManager(String fullname, String username, String email, String password, Long serviceId) {
        TechnicalManager technicalManager = new TechnicalManager();
        TechnicalService technicalService = technicalServiceService.getTechnicalServiceById(serviceId);

        technicalManager.setPassword(password);
        technicalManager.setUserName(username);
        technicalManager.setFullName(fullname);
        technicalManager.setEmail(email);
        technicalManager.setTechnicalService(technicalService);

        repository.save(technicalManager);

        return technicalManager;
    }

    /*
    Method for creating technical manager by entity and saving it to DB
     */
    @Override
    public void createTechnicalManager(TechnicalManager technicalManager) {

        repository.save(technicalManager);
    }

    /*
    Method for updating technical manager info by entity
     */
    @Override
    public void updateTechnicalManager(TechnicalManager technicalManager) {

        repository.save(technicalManager);
    }

    /*
    Method for updating technical manager info by fields parameter (optional)
     */
    @Override
    public TechnicalManagerDto updateTechnicalManager(Long id, String fullname, String username, String password) {

        TechnicalManager technicalManager = getTechnicalManager(id);

        //managerDto.setId(id);

        if (fullname != null) {
            technicalManager.setFullName(fullname);
        }

        if (username != null) {
            technicalManager.setUserName(username);
        }

        if (password != null) {
            technicalManager.setPassword(password);
        }

        repository.save(technicalManager);

        return  getTechnicalManagerDto(id);
    }

    /*
    Method for updating technical manager info by DTO
     */
    @Override
    public void updateTechnicalManager(TechnicalManagerDto technicalManagerDto) {
        TechnicalManager technicalManager = new TechnicalManager();

        technicalManager.setId(technicalManagerDto.getId());
        technicalManager.setTechnicalService(technicalManagerDto.getTechnicalService());
        technicalManager.setWorkers(technicalManagerDto.getWorkers());
        technicalManager.setEmail(technicalManagerDto.getEmail());
        technicalManager.setFullName(technicalManagerDto.getFullName());
        technicalManager.setUserName(technicalManagerDto.getUserName());
        technicalManager.setPassword(technicalManagerDto.getPassword());

        repository.save(technicalManager);
    }

    /*
    Method for deleting technical manager from DB by its id
     */
    @Override
    public void deleteTechnicalManager(Long id) {
        repository.deleteById(id);
    }

    /*
    Method fot getting technical manager DTO by id
     */
    @Override
    public TechnicalManagerDto getTechnicalManagerDto(Long id) {
        TechnicalManager temp;
        TechnicalManagerDto managerDto;

        temp = (TechnicalManager) repository.getTechnicalManagerById(id);
        managerDto = new TechnicalManagerDto(temp.getId(), temp.getEmail(), temp.getPassword(), temp.getFullName(), temp.getUserName(), temp.getTechnicalService(), temp.getWorkers());

        logger.info("Successfully read TechManager info.");

        return managerDto;
    }

    /*
    Method fot getting technical manager entity by id
    */
    @Override
    public TechnicalManager getTechnicalManager(Long id) {
        return repository.getTechnicalManagerById(id);
    }
}
