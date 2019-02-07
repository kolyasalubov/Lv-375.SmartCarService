package ua.ita.smartcarservice.entity.sensors.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "fact_tire_pressure")
public class TirePressureEntity extends BaseSensorEntity {

    @Column(nullable = false)
    protected double valueFrontLeft;

    @Column(nullable = false)
    protected double valueFrontRight;

    @Column(nullable = false)
    protected double valueBackLeft;

    @Column(nullable = false)
    protected double valueBackRight;

}
