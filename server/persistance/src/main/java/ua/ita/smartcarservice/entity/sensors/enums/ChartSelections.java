package ua.ita.smartcarservice.entity.sensors.enums;

public enum ChartSelections {

    YEAR("year"),
    MONTH("month"),
    DAY("day"),
    TIME("time"),

    LAST("last");

    private String period;

    ChartSelections(String period) {
        this.period = period;
    }

    public String toString() {
        return period;
    }

}
