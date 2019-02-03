package com.smartcarservice.ua.SmartCarService.repository.sensors;


import com.smartcarservice.ua.SmartCarService.entity.sensors.data.BatteryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends SensorRepository<BatteryEntity> {
}
