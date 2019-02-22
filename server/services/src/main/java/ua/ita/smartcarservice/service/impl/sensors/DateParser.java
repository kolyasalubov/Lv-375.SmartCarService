package ua.ita.smartcarservice.service.impl.sensors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static LocalDateTime parseDateToLocal(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(strDate, formatter);
    }

}
