package ua.ita.smartcarservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUsername(String username);
	
	boolean existsByUsername(String username);

	List<UserEntity> getByUserTechnicalServiceAndWorkersSkill(UserTechnicalService userTechnicalService,
															  WorkersSkill workersSkill);

	List<UserEntity> getByUserTechnicalService(UserTechnicalService userTechnicalService);
}
