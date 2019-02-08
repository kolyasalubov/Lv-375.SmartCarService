package ua.ita.smartcarservice.serviceImpl;

import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
import ua.ita.smartcarservice.repository.TechnicalServiceRepository;
import ua.ita.smartcarservice.service.TechnicalServiceService;
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
