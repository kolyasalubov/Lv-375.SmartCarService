package ua.ita.smartcarservice.repository;

import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalManagerRepository extends JpaRepository<TechnicalManager, Long> {
    TechnicalManager getTechnicalManagerById(Long id);
}
