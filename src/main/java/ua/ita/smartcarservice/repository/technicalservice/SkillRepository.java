package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    SkillEntity getBySkillId(SkillEntity skillEntity);

    @Query("select distinct s from SkillEntity as s left join WorkersSkill as w on w.skill = s.skillId " +
            "where w.workerId = 1"
            )
    List<SkillEntity> getSkillNameBySto();
}
