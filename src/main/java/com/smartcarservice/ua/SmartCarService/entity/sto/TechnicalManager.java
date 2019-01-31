package com.smartcarservice.ua.SmartCarService.entity.sto;

import com.smartcarservice.ua.SmartCarService.entity.UserBaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "sto_manager")
public class TechnicalManager extends UserBaseEntity {

    public TechnicalManager(){}

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long managerId;

    @OneToOne
    @JoinColumn(name = "sto_id", nullable = false)
    private TechnicalService technicalService;

    @OneToMany(mappedBy = "technicalManager")
    Set<Worker> workers;
}