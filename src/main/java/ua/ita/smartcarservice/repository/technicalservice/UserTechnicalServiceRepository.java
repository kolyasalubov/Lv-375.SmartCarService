package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;

@Repository
public interface UserTechnicalServiceRepository extends JpaRepository<UserTechnicalService, Long> {
}
