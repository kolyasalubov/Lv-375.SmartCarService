package ua.ita.smartcarservice.entity.sensors.data;

public enum SensorTypes {
    SPEED("speed"),
    BATTERY("battery"),
    FUEL("fuel"),
    MILEAGE("mileage"),
    TIRE_PRESSURE("tire pressure"),
    OIL_PRESSURE("oil pressure"),
    OIL_LEVEL("oil level");

    private String sensorType;

    private SensorTypes(String sensorType){
        this.sensorType = sensorType;
    }

    public String toString() {
        return sensorType;
    }
}
