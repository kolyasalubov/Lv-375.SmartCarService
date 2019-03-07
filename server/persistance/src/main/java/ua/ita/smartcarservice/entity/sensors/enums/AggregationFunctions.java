package ua.ita.smartcarservice.entity.sensors.enums;

public enum  AggregationFunctions {

    MIN("min"),
    MAX("max"),
    AVG("avg");

    private String function;

    private AggregationFunctions(String function) {
        this.function = function;
    }

    public String toString() {
        return function;
    }

}
