package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

@Repository
public interface TechnicalServiceRepository extends JpaRepository<TechnicalServiceEntity, Long> {
    TechnicalServiceEntity getByTechnicalServiceId(Long id);
}
