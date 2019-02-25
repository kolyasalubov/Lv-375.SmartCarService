package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;

@Repository
public interface WorkersSkillRepository extends JpaRepository<WorkersSkill, Long> {
    WorkersSkill getBySkill(SkillEntity skillEntity);
    /*@Query(value = "select * from skills as s " +
            "left join workers_skills as ws on s.skill_id = ws.skill_id " +
            "where ws.worker_id = :worker_id", nativeQuery = true)
    SkillEntity getSkillByWorkerId(@Param("worker_id") Long worker_id);*/

    @Query(value = "select s from SkillEntity  s " +
            "left join WorkersSkill as ws " +
            "where ws.workerId = :workerId")
    SkillEntity getSkillByWorkerId(@Param("workerId") UserEntity workerId);

    WorkersSkill getByWorkerId(UserEntity workerId);
}
