package ua.ita.smartcarservice.entity.technicalservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "skills")
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "workerId")
    private List<WorkersSkill> workersSkill;

    @JsonIgnore
    @OneToMany(mappedBy = "skill")
    List<WorkType> workTypes;

    public SkillEntity() {
    }

    public SkillEntity(String name, Long requiredTime) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillEntity skill = (SkillEntity) o;
        return name.equals(skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
