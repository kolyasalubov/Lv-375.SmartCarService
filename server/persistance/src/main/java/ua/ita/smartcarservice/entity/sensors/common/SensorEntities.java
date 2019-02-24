package ua.ita.smartcarservice.entity.sensors.common;

import ua.ita.smartcarservice.entity.sensors.*;

public enum SensorEntities {

    SPEED(SensorTypes.SPEED.toString(), SpeedEntity.class),
    BATTERY(SensorTypes.BATTERY.toString(), BatteryEntity.class),
    FUEL(SensorTypes.FUEL.toString(), FuelEntity.class),
    MILEAGE(SensorTypes.MILEAGE.toString(), MileageEntity.class),
    OIL_PRESSURE(SensorTypes.OIL_PRESSURE.toString(), OilPressureEntity.class),
    OIL_LEVEL(SensorTypes.OIL_LEVEL.toString(), OilLevelEntity.class),

    TIRE_PRESSURE(SensorTypes.TIRE_PRESSURE.toString(), TirePressureEntity.class),

    GLASS_WASHER_FLUID(SensorTypes.GLASS_WASHER_FLUID.toString(), GlassWasherFluidEntity.class),
    COOLANT(SensorTypes.COOLANT.toString(), CoolantEntity.class),
    BRAKE_FLUID(SensorTypes.BRAKE_FLUID.toString(), BrakeFluidEntity.class);

    private String sensorType;
    private Class sensorEntity;

    private SensorEntities(String sensorType, Class sensorEntity) {
        this.sensorEntity = sensorEntity;
        this.sensorType = sensorType;
    }

    public Class getSensorEntity() {
        return sensorEntity;
    }

    public String getSensorType() {
        return sensorType;
    }
}
