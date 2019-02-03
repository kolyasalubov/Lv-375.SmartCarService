package com.smartcarservice.ua.SmartCarService.entity.sensors.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class SensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Car car;

    @Column(nullable = false, columnDefinition = "TIMESTAMP" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime date;

    @Column(nullable = false)
    protected double value;

}
