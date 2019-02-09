package ua.ita.smartcarservice.entity.sensors.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fact_speed")
public class SpeedEntity extends SensorEntity implements ISensorEntity {

}
