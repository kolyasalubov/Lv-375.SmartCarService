package ua.ita.smartcarservice.repository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.sensors.data.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SensorRepositoryFactory {

//    public enum Sensors {
//
//        SPEED(SensorTypes.SPEED.toString(), speedRepository),
//        BATTERY(SensorTypes.BATTERY.toString(), batteryRepository),
//        FUEL(SensorTypes.FUEL.toString(), fuelRepository),
//        MILEAGE(SensorTypes.MILEAGE.toString(), mileageRepository),
//        TIRE_PRESSURE(SensorTypes.TIRE_PRESSURE.toString(), tirePressureRepository),
//        OIL_PRESSURE(SensorTypes.OIL_PRESSURE.toString(), oilPressureRepository),
//        OIL_LEVEL(SensorTypes.OIL_LEVEL.toString(), oilLevelRepository);
//
//        private String sensorType;
//        private SensorRepository<? extends BaseSensorEntity> sensorRepository;
//
//        private Sensors(String sensorType, SensorRepository<? extends BaseSensorEntity> sensorRepository){
//            this.sensorRepository = sensorRepository;
//            this.sensorType = sensorType;
//        }
//
//        public SensorRepository<? extends BaseSensorEntity> getSensorRepository() {
//            return sensorRepository;
//        }
//
//        public String getSensorType() {
//            return sensorType;
//        }
//    }

    @Autowired
    private SensorRepository<SpeedEntity> speedRepository;

    @Autowired
    private SensorRepository<BatteryEntity> batteryRepository;

    @Autowired
    private SensorRepository<FuelEntity> fuelRepository;

    @Autowired
    private SensorRepository<MileageEntity> mileageRepository;

    @Autowired
    private SensorRepository<TirePressureEntity> tirePressureRepository;

    @Autowired
    private SensorRepository<OilPressureEntity> oilPressureRepository;

    @Autowired
    private SensorRepository<OilLevelEntity> oilLevelRepository;

    private Map <String, SensorRepository<? extends BaseSensorEntity> > repFactory = new HashMap<>();
    private Map <String, ISensorEntity> entityFactory = new HashMap<>();


    private void factoryInit(){
        repFactory.put(SensorTypes.SPEED.toString(),speedRepository);
        repFactory.put(SensorTypes.BATTERY.toString(), batteryRepository);
        repFactory.put(SensorTypes.FUEL.toString(), fuelRepository);
        repFactory.put(SensorTypes.MILEAGE.toString(), mileageRepository);
        repFactory.put(SensorTypes.TIRE_PRESSURE.toString(), tirePressureRepository);
        repFactory.put(SensorTypes.OIL_PRESSURE.toString(), oilPressureRepository);
        repFactory.put(SensorTypes.OIL_LEVEL.toString(), oilLevelRepository);
    }

    private void entityInit(){
        entityFactory.put(SensorTypes.SPEED.toString(), new SpeedEntity());
        entityFactory.put(SensorTypes.BATTERY.toString(), new BatteryEntity());
        entityFactory.put(SensorTypes.FUEL.toString(), new FuelEntity());
        entityFactory.put(SensorTypes.MILEAGE.toString(), new MileageEntity());
        entityFactory.put(SensorTypes.TIRE_PRESSURE.toString(), new TirePressureEntity());
        entityFactory.put(SensorTypes.OIL_PRESSURE.toString(), new OilPressureEntity());
        entityFactory.put(SensorTypes.OIL_LEVEL.toString(), new OilLevelEntity());
    }

    public SensorRepository<? extends BaseSensorEntity> getRepository(String type){
        if(repFactory.size() == 0)
            factoryInit();
        return repFactory.get(type);
    }

    public ISensorEntity getEntity (String type){
        entityInit();
        return entityFactory.get(type);
    }

}
