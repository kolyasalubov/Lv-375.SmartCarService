package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    //@Query("select distinct s.name from Skill as s left join Worker as w on w.skill = s.skillId where w.technicalService.stoId = :stoId")
    //List<String> getSkillNameBySto(@Param("stoId") Long stoId);
}
