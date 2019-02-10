package ua.ita.smartcarservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;
import ua.ita.smartcarservice.entity.RoleEntity;
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
/*
	@Query("select a from user as u join user_roles as r on user.id = r.user_id" +
			" where r.role_id = :id")
	List<UserEntity> getByRoles(@Param("id") Long roleId);*/

    List<UserEntity> getByRoles(RoleEntity roleEntity);
}
