package ua.ita.smartcarservice.entity.sensors.common;

public enum BrakeFluid {
    LEVEL("level"),
    HUMIDITY("humidity");

    private String value;

    private BrakeFluid(String tire) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
