package ua.ita.smartcarservice.entity.sensors.data;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "fact_tire_pressure")
public class TirePressureEntity extends BaseSensorEntity implements ISensorEntity {

    @Column(nullable = false)
    protected double valueFrontLeft;

    @Column(nullable = false)
    protected double valueFrontRight;

    @Column(nullable = false)
    protected double valueBackLeft;

    @Column(nullable = false)
    protected double valueBackRight;


    @Override
    public void setValue(double value) {

    }

    @Override
    public double getValue() {
        return 0;
    }
}
