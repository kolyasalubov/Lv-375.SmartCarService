package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.repository.sensors.SensorRepository;
import com.smartcarservice.ua.SmartCarService.repository.sensors.SpeedRepository;

public enum Sensors {
    SPEED(SpeedRepository),
    BATTERY,
    FUEL,
    MILEAGE,
    TIRE_PRESSURE,
    OIL_PRESSURE,
    OIL_LEVEL

    private SensorRepository
}
