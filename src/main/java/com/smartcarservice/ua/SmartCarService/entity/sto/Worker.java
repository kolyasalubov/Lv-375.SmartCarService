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
    @JsonBackReference
    @JoinColumn(name = "manager_id", nullable = false)
    private TechnicalManager technicalManager;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @JsonIgnore
    @OneToMany(mappedBy = "worker")
    Set<Session> sessions;

}