package com.smartcarservice.ua.SmartCarService.entity.sensors.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fact_tire_pressure")
public class TirePressureEntity extends RecordEntity {

    @Column (length = 5, nullable = false)
    private String tireOrder;    // front/back

    @Column(length = 5, nullable = false)
    private String tireSide;    // left/right
}
