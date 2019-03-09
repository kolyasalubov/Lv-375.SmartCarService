package ua.ita.smartcarservice.repository.sensors.common;


// TODO delete or change
public enum ChartSensorQueries {

    FIND_ALL_DATA_BY_DAY("/day"),

    FIND_AVG_VALUES_BY_MONTH("/month"),

    FIND_MAX_VALUE_BY_MONTH("/month/max"),

    FIND_MIN_VALUE_BY_MONTH("/month/min"),

    FIND_AVG_VALUES_BY_YEAR("/year"),

    FIND_MAX_VALUE_BY_YEAR("/year/max"),

    FIND_MIN_VALUE_BY_YEAR("/year/min"),

    FIND_LAST_VALUE("/last");


    private String selection;

    private ChartSensorQueries(String selection){
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }
}
