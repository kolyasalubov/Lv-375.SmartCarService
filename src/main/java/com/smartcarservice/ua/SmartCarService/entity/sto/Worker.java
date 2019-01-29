package com.smartcarservice.ua.SmartCarService.entity;


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
    @JoinColumn(name = "manager_id", nullable = false)
    private TechnicalManager technicalManager;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @OneToMany(mappedBy = "worker")
    Set<Session> sessions;

}