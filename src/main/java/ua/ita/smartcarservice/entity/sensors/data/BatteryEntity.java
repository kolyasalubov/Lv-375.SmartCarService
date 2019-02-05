package ua.ita.smartcarservice.entity.sensors.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fact_battery")

public class BatteryEntity extends SensorEntity implements ISensorEntity {

}
