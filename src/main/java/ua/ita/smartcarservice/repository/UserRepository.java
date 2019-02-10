package ua.ita.smartcarservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUsername(String username);
	
	boolean existsByUsername(String username);

	@Query("select u from UserEntity as u left join WorkersSkill as w " +
			"on u.id = w.workerId left join UserTechnicalService as ut " +
			"on u.id = ut.userId where w.skill.name = :name and ut.technicalServiceId.technicalServiceId = :stoId")
	List<UserEntity> getByUserTechnicalServiceAndWorkersSkill(@Param("name") String name, @Param("stoId") Long stoId);

	List<UserEntity> getByUserTechnicalService(UserTechnicalService userTechnicalService);

}
