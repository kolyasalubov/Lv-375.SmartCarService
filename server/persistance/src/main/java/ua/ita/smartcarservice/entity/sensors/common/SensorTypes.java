package ua.ita.smartcarservice.entity.sensors.common;

public enum SensorTypes {
    SPEED("speed"), // line
    BATTERY("battery"), // doughnut
    FUEL("fuel"), // bar
    MILEAGE("mileage"), // line
    OIL_PRESSURE("oil pressure"), // line
    OIL_LEVEL("oil level"), // bar

    TIRE_PRESSURE("tire pressure"), // line

    GLASS_WASHER_FLUID("glass washer fluid"),
    COOLANT("coolant"),
    BRAKE_FLUID("brake fluid");

    private String sensorType;

    private SensorTypes(String sensorType) {
        this.sensorType = sensorType;
    }

    public String toString() {
        return sensorType;
    }
}
