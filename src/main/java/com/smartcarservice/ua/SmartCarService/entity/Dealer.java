package com.smartcarservice.ua.SmartCarService.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "dealer")
public class Dealer extends UserBaseEntity {




    @Column(length = 15, nullable = false, unique = true)
    private String phone_number;


    @Column(length = 100, nullable = false, unique = false)
    private String address;


    @Column(length = 100, nullable = false, unique = true)
    private String edr;





    @OneToMany(mappedBy = "dealer")
    private Set<TechnicalService>technicalServices;


    @OneToMany(mappedBy = "dealer")
   private Set<Car> cars;


    @OneToMany(mappedBy = "dealer")
    private Set<SalesManager> salesManagers;


}
