package ua.ita.smartcarservice.entity.technicalservice;

import lombok.Data;
import ua.ita.smartcarservice.entity.booking.WorkDependency;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "work_type")
public class WorkType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long requiredTime;

    @Column(nullable = false)
    private Long cost;

    @ManyToOne
    @JoinColumn(name = "skillId", nullable = false)
    private SkillEntity skill;

    @OneToMany(mappedBy = "mainWork")
    List<WorkDependency> listOfMainWork;

    @OneToMany(mappedBy = "dependentWork")
    List<WorkDependency> listOfDependentWork;
}
