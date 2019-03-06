package ua.ita.smartcarservice.entity.sensors.common;

public enum SensorElements {
    DATE("date"),
    CAR("car"),
    ID("id"),
    VALUE("value"),

    YEAR("year"),
    MONTH("month"),
    DAY("day"),
    TIME("time"),

    MIN("min"),
    MAX("max");

    private String constant;

    private SensorElements(String constant) {
        this.constant = constant;
    }

    public String toString() {
        return constant;
    }


    //    PART_OF_SENSORS(Arrays.asList("", "f")),

//    DATE("date"),
//    CAR("car"),
//    ID("id"),
//    VALUE("value"),
//
//    YEAR("year"),
//    MONTH("month"),
//    DAY("day"),
//    TIME("time"),
//
//    MIN("min"),
//    MAX("max");
//
//    private List<String> propertiesList;
//
//    private SensorElements(List<String> propertiesList) {
//        this.propertiesList = propertiesList;
//    }
//
//    public List<String> getPropertiesList() {
//        return propertiesList;
//    }
}
