package com.smartcarservice.ua.SmartCarService.repository.sensors;


import com.smartcarservice.ua.SmartCarService.entity.sensors.data.FuelEntity;
import com.smartcarservice.ua.SmartCarService.entity.sensors.data.SpeedEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeedRepository extends RecordRepository<SpeedEntity> {
}
