package ua.ita.smartcarservice.entity.sensors;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class SensorEntity extends BaseSensorEntity implements ISingleValueEntity {

    @Column(nullable = false)
    protected double value;

}