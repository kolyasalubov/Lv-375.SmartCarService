package ua.ita.smartcarservice.entity.sensors.data;

import lombok.Data;
import ua.ita.smartcarservice.entity.Car;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "fact_oil_level")
public class OilLevelEntity extends SensorEntity implements ISensorEntity {

}