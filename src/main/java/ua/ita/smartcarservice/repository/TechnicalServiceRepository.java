package ua.ita.smartcarservice.repository;

import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalServiceRepository extends JpaRepository<TechnicalService, Long> {
    TechnicalService getByTechnicalManager(TechnicalManager technicalManager);
}
