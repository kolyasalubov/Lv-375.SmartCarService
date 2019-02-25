package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;

import java.util.List;

@Repository
public interface UserTechnicalServiceRepository extends JpaRepository<UserTechnicalService, Long> {

    @Query(value = "select u from UserEntity as u " +
            "left join UserTechnicalService as us " +
            //"left join u.roles as r " +
            "where us.technicalServiceId = :technicalServiceId " )
            //"and r.name = 'ROLE_TECHNICAL_MANAGER'")
    UserEntity getTechnicalServiceManagerByServiceId(@Param("technicalServiceId") TechnicalServiceEntity technicalServiceId);

    /*
    //u.id, u.username, u.password, u.email, u.full_name, u.number_phone
    @Query(value = "select u.id, u.username, u.password, u.email, u.full_name, u.number_phone from user as u " +
            "left join users_service as us on u.id = us.user_id " +
            "left join user_roles as ur on u.id = ur.user_id " +
            "left join role as r on ur.role_id = role_id " +
            "where us.technical_service_id = :technical_service_id " +
            "and r.name = 'ROLE_TECHNICAL_MANAGER'", nativeQuery = true)
    UserEntity getTechnicalServiceManagerByServiceId(@Param("technical_service_id") Long technical_service_id);
*/

    UserTechnicalService getByTechnicalServiceId(TechnicalServiceEntity technicalServiceEntity);

    UserTechnicalService getById(Long id);

    void deleteByTechnicalServiceId(Long id);

    @Query("select ut.technicalServiceId.name from UserTechnicalService as ut " +
            "left join Car as c on ut.userId = c.user " +
            "where c.id = :id")
    String findTechnicalServiceByCarId(@Param("id") Long id);
}
