package com.smartcarservice.ua.SmartCarService.entity.sto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.smartcarservice.ua.SmartCarService.entity.UserBaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "sto_manager")
public class TechnicalManager extends UserBaseEntity {

    public TechnicalManager(){}

    @OneToOne
    @JoinColumn(name = "sto_id", nullable = false)
    private TechnicalService technicalService;

    @JsonManagedReference
    @OneToMany(mappedBy = "technicalManager")
    Set<Worker> workers;
}