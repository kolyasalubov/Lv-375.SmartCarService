package com.smartcarservice.ua.SmartCarService.entity.sto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workerId;

    @Column(length = 100, nullable = false, unique = true)
    private String fullName;

    @ManyToOne
    //@JsonIgnore //optional
    @JsonBackReference
    @JoinColumn(name = "manager_id", nullable = false)
    private TechnicalManager technicalManager;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sto_id", nullable = false)
    private TechnicalService technicalService;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public TechnicalManager getTechnicalManager() {
        return technicalManager;
    }

    public void setTechnicalManager(TechnicalManager technicalManager) {
        this.technicalManager = technicalManager;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "worker")
    Set<Session> sessions;

}