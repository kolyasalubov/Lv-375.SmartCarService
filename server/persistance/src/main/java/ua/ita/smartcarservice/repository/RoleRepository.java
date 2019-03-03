package ua.ita.smartcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import ua.ita.smartcarservice.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	
	//Optional<RoleEntity> findByName(Roles roleName);
	
	RoleEntity findByName(String roleName);
	
	

}
