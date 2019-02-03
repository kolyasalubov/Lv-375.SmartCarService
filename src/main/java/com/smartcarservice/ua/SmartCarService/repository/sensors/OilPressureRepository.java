package com.smartcarservice.ua.SmartCarService.repository.sensors;


import com.smartcarservice.ua.SmartCarService.entity.sensors.data.FuelEntity;
import com.smartcarservice.ua.SmartCarService.entity.sensors.data.OilPressureEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface OilPressureRepository extends RecordRepository<OilPressureEntity> {
}
