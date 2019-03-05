package ua.ita.smartcarservice.entity.technicalservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.entity.alerts.Alerts;
import ua.ita.smartcarservice.entity.booking.WorkDependency;
import ua.ita.smartcarservice.entity.booking.WorkTime;

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
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillEntity skill;

    @JsonIgnore
    @OneToMany(mappedBy = "mainWork")
    List<WorkDependency> listOfMainWork;

    @JsonIgnore
    @OneToMany(mappedBy = "dependentWork")
    List<WorkDependency> listOfDependentWork;

    @JsonIgnore
    @OneToMany(mappedBy = "work")
    List<WorkTime> workTimes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            mappedBy = "workType")
    private List<Alerts> alerts;
}

