package ua.ita.smartcarservice.entity.sensors.common;

public enum Tires {
    FRONT_LEFT("frontLeft", "valueFrontLeft"),
    FRONT_RIGHT("frontRight", "valueFrontRight"),
    BACK_LEFT("backLeft", "valueBackLeft"),
    BACK_RIGHT("backRight", "valueBackRight");

    private String name;
    private String value;

    private Tires(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public String getName() {
        return name;
    }
}
