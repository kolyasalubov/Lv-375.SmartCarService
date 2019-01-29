package com.smartcarservice.ua.SmartCarService.entity.sensors;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
@Table(name = "dim_date")
public class DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Time time;

    @Column(nullable = false)
    private int day;    // 1-31

    @Column(nullable = false)
    private int month;     // 1-12

    @Column(nullable = false)
    private int year;

//    TODO fix foreign key
//    @OneToOne(fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL,
//            mappedBy = "dateEntity")
//    private RecordEntity record;

}
