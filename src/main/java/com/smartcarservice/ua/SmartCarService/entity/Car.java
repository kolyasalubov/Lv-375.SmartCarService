package com.smartcarservice.ua.SmartCarService.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "cars")
@NoArgsConstructor
public class Car {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long carId;

    @Column(nullable = false)
    @Getter
    @Setter
    private String brand;

    @Column(nullable = false)
    @Getter
    @Setter
    private String model;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Data year; 
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String color;                //create enum with colors?
    
    @Column(nullable = false)
    @Getter
    @Setter
    private Double price; 
    
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String win; 
      
    @Column(nullable = true)
    @Getter
    @Setter
    private Long diller_id;
    
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @Getter
    @Setter
    private CarOwner carOwner;
    


}
