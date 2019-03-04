package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;

import java.util.Collection;
import java.util.List;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {

    @Query("select max(w.workId) from WorkType as w")
    Long findMaxId();

    /**
     * Method returns list of works which are dependent on skill
     *
     * @param name - skillName
     * @return list of work
     */
    @Query("select w from WorkType as w where w.skill.name = :name")
    List<WorkType> findAllWorkBySkill(@Param("name") String name);

    @Query("select w from WorkType as w where w.workId in (:worksId)")
    List<WorkType> findAllById(@Param("worksId") Collection<Long> worksId);

}
