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

    UserTechnicalService getByTechnicalServiceId(TechnicalServiceEntity technicalServiceEntity);
    UserTechnicalService getById(Long id);
    void deleteByTechnicalServiceId(Long id);

    @Query("select ut.technicalServiceId.name from UserTechnicalService as ut " +
            "left join Car as c on ut.userId = c.user " +
            "where c.id = :id")
    String findTechnicalServiceByCarId(@Param("id") Long id);

}
