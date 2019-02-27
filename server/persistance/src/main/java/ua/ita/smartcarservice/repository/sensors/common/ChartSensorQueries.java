package ua.ita.smartcarservice.repository.sensors.common;

public enum ChartSensorQueries {

    FIND_ALL_DATA_BY_DAY("/day", "SELECT TIME(t.date), t.value FROM #{#entityName} t WHERE t.car.id = :carId " +
            "AND DAY(t.date) = DAY(:date) AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "ORDER BY TIME(t.date)"),

    FIND_AVG_VALUES_BY_MONTH("/month", "SELECT DAY(t.date), AVG(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY DAY(t.date)" +
            "ORDER BY DAY(t.date)"),

    FIND_MAX_VALUE_BY_MONTH("/month/max", "SELECT DAY(t.date), MAX(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY DAY(t.date)" +
            "ORDER BY DAY(t.date)"),

    FIND_MIN_VALUE_BY_MONTH("/month/min", "SELECT DAY(t.date), MIN(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY DAY(t.date)" +
            "ORDER BY DAY(t.date)"),

    FIND_AVG_VALUES_BY_YEAR("/year", "SELECT MONTH(t.date), AVG(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY MONTH(t.date)" +
            "ORDER BY MONTH(t.date)"),

    FIND_MAX_VALUE_BY_YEAR("/year/max", "SELECT MONTH(t.date), MAX(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY MONTH(t.date)" +
            "ORDER BY MONTH(t.date)"),

    FIND_MIN_VALUE_BY_YEAR("/year/min", "SELECT MONTH(t.date), MIN(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY MONTH(t.date)" +
            "ORDER BY MONTH(t.date)"),

    FIND_LAST_VALUE("/last",  "SELECT t.value FROM #{#entityName} t WHERE t.id = " +
            "(SELECT MAX(e.id) FROM #{#entityName} e " +
            "WHERE e.car.id = :carId)");


    private String key;
    private String query;

    private ChartSensorQueries(String key, String query){
        this.key = key;
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public String getKey() {
        return key;
    }
}
