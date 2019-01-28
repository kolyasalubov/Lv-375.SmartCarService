package com.smartcarservice.ua.SmartCarService.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "sto")
public class TechnicalService {

    public TechnicalService(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stoId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String address;

    @OneToMany(mappedBy = "technicalService")
    Set<TechnicalManager> technicalManagers;

}
