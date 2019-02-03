package com.smartcarservice.ua.SmartCarService.repository.sensors;


import com.smartcarservice.ua.SmartCarService.entity.sensors.data.OilLevelEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OilLevelRepository extends SensorRepository<OilLevelEntity> {
}
