package ua.ita.smartcarservice.entity.sensors;

import lombok.Data;
import ua.ita.smartcarservice.entity.sensors.common.BrakeFluid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

@Data
@Entity
@Table(name = "fact_brake_fluid")
public class BrakeFluidEntity extends BaseSensorEntity implements ISensorEntity {

    @Column(nullable = false)
    protected double level;

    @Column(nullable = false)
    protected double humidity;

    public ISensorEntity setValues(ISensorEntity entity, Map<String, Double> values) {
        BrakeFluidEntity iEntity = (BrakeFluidEntity) entity;
        iEntity.setLevel(values.get(BrakeFluid.LEVEL.toString()));
        iEntity.setHumidity(values.get(BrakeFluid.HUMIDITY.toString()));

        return iEntity;
    }

}
