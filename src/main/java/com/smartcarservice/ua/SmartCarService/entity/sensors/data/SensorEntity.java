package com.smartcarservice.ua.SmartCarService.entity.sensors.data;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class SensorEntity extends BaseSensorEntity{

    @Column(nullable = false)
    protected double value;

}
