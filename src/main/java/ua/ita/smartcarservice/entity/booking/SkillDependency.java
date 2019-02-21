package ua.ita.smartcarservice.entity.booking;

import lombok.Data;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "skills_dependency")
public class SkillDependency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dependencyId;

    @ManyToOne
    @JoinColumn(name = "main_skill", nullable = false)
    SkillEntity mainSkill;

    @ManyToOne
    @JoinColumn(name = "dependent_skill", nullable = false)
    SkillEntity dependentSkill;

}
