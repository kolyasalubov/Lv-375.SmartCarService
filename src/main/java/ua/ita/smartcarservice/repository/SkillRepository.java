package ua.ita.smartcarservice.repository;

import ua.ita.smartcarservice.entity.sto.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Skill getSkillBySkillId(Long id);
}
