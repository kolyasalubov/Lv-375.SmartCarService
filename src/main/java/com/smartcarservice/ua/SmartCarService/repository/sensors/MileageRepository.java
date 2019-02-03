package com.smartcarservice.ua.SmartCarService.repository.sensors;


import com.smartcarservice.ua.SmartCarService.entity.sensors.data.MileageEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MileageRepository extends SensorRepository<MileageEntity> {
}
