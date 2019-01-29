package com.smartcarservice.ua.SmartCarService.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JoinColumn(name="dealer", nullable=false)
//    @ManyToMany
//    private Dealer dealer;

    @Column(length = 20, nullable = false, unique = false)
    private String brand;

    @Column(length = 20, nullable = false, unique = false)
    private String model;


    @Column(length = 20, nullable = false, unique = false)
    private String graduation_year;


    @Column(length = 100, nullable = false, unique = true)
    private String number;

    @Column(nullable = true, unique = false)
    private Double price;


    @Column(nullable = false, unique = true)
    private Long vin;

    @Column(nullable = true, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_guarantee;

    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = true)
    private Dealer dealer;


}
