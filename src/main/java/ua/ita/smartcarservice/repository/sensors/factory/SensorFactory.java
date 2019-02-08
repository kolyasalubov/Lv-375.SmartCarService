package ua.ita.smartcarservice.repository.sensors.factory;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.sensors.data.*;
import ua.ita.smartcarservice.repository.sensors.*;
import ua.ita.smartcarservice.service.SensorService;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SensorFactory {

    @Autowired
    private SpeedRepository speedRepository;

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private MileageRepository mileageRepository;

    @Autowired
    private OilPressureRepository oilPressureRepository;

    @Autowired
    private OilLevelRepository oilLevelRepository;

    @Autowired
    @Qualifier("basic")
    private SensorService sensorService;

    @Autowired
    @Qualifier("tire")
    private SensorService tireService;

    private Map <String, SensorService> serviceFactory = new HashMap<>();
    private Map <String, SensorRepository<? extends BaseSensorEntity> > repFactory = new HashMap<>();
    private Map <String, Class> entityFactory = new HashMap<>();


    public SensorFactory(){
        entityInit();
    }

    private void entityInit(){
        for(SensorEntities entity : SensorEntities.values()){
            entityFactory.put(entity.getSensorType(), entity.getSensorEntity());
        }
    }

    public ISensorEntity getEntity (String type){
        try {
            return (ISensorEntity) entityFactory.get(type).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } return null;
    }


    public SensorRepository<? extends BaseSensorEntity> getRepository(String type){
        if(repFactory.size() == 0)
            repInit();
        return repFactory.get(type);
    }

    private void repInit(){
        repFactory.put(SensorTypes.SPEED.toString(),speedRepository);
        repFactory.put(SensorTypes.BATTERY.toString(), batteryRepository);
        repFactory.put(SensorTypes.FUEL.toString(), fuelRepository);
        repFactory.put(SensorTypes.MILEAGE.toString(), mileageRepository);
        repFactory.put(SensorTypes.OIL_PRESSURE.toString(), oilPressureRepository);
        repFactory.put(SensorTypes.OIL_LEVEL.toString(), oilLevelRepository);
    }

    public SensorService getService(String type){
        if(type.equals(SensorTypes.TIRE_PRESSURE.toString()))
            return tireService;
        return sensorService;
    }


}
