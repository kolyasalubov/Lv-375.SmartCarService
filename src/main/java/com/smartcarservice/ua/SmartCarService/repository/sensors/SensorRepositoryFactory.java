package com.smartcarservice.ua.SmartCarService.repository.sensors;

import com.smartcarservice.ua.SmartCarService.entity.sensors.data.SensorEntity;
import com.smartcarservice.ua.SmartCarService.entity.sensors.data.SensorTypes;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class SensorRepositoryFactory {

    public enum Sensors {

        SPEED(SensorTypes.SPEED.toString(), speedRepository),
        BATTERY(SensorTypes.BATTERY.toString(), batteryRepository),
        FUEL(SensorTypes.FUEL.toString(), fuelRepository),
        MILEAGE(SensorTypes.MILEAGE.toString(), mileageRepository),
        TIRE_PRESSURE(SensorTypes.TIRE_PRESSURE.toString(), tirePressureRepository),
        OIL_PRESSURE(SensorTypes.OIL_PRESSURE.toString(), oilPressureRepository),
        OIL_LEVEL(SensorTypes.OIL_LEVEL.toString(), oilLevelRepository);

        private String sensorType;
        private SensorRepository<? extends SensorEntity> sensorRepository;

        private Sensors(String sensorType, SensorRepository<? extends SensorEntity> sensorRepository){
            this.sensorRepository = sensorRepository;
            this.sensorType = sensorType;
        }

        public SensorRepository<? extends SensorEntity> getSensorRepository() {
            return sensorRepository;
        }

        public String getSensorType() {
            return sensorType;
        }
    }

    @Autowired
    public static SpeedRepository speedRepository;

    @Autowired
    public static BatteryRepository batteryRepository;

    @Autowired
    public static FuelRepository fuelRepository;

    @Autowired
    public static MileageRepository mileageRepository;

    @Autowired
    public static TirePressureRepository tirePressureRepository;

    @Autowired
    public static OilPressureRepository oilPressureRepository;

    @Autowired
    public static OilLevelRepository oilLevelRepository;

    private Map<String, SensorRepository<? extends SensorEntity>> factory = new HashMap<>();

    public SensorRepositoryFactory(){
        for (Sensors sensor : Sensors.values()) {
            factory.put(sensor.getSensorType(), sensor.getSensorRepository());
        }
    }
}
