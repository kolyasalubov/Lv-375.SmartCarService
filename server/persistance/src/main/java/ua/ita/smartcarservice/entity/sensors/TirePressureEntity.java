package ua.ita.smartcarservice.entity.sensors;

import lombok.Data;
import ua.ita.smartcarservice.entity.sensors.common.Tires;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

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

    public ISensorEntity setValues(ISensorEntity entity, Map<String, Double> values) {
        TirePressureEntity iEntity = (TirePressureEntity) entity;
        iEntity.setValueBackRight(values.get(Tires.BACK_RIGHT.toString()));
        iEntity.setValueBackLeft(values.get(Tires.BACK_LEFT.toString()));
        iEntity.setValueFrontRight(values.get(Tires.FRONT_RIGHT.toString()));
        iEntity.setValueFrontLeft(values.get(Tires.FRONT_LEFT.toString()));

        return iEntity;
    }

}
