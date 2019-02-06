package ua.ita.smartcarservice.service.impl;

import ua.ita.smartcarservice.entity.sensors.data.ISensorEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class LabelsProvider {

    private final int[] MONTH_WITH_31_DAY = {1, 3, 5, 7, 8, 10, 12};
    private final int[] MONTH_WITH_30_DAY = {4, 6, 9, 11};

    private final int DAYS_31 = 31;
    private final int DAYS_30 = 30;
    private final int DAYS_29 = 29;
    private final int DAYS_28 = 28;

    public List<String> getDays(LocalDateTime date){
        int month = date.getMonthValue();           // TODO ?
        int quantity;
        if (isInArr(MONTH_WITH_31_DAY, month))
            quantity = DAYS_31;
        else if(isInArr(MONTH_WITH_30_DAY, month))
            quantity = DAYS_30;
        else {
            if (date.getYear() % 4 == 0)
                quantity = DAYS_29;
            else
                quantity = DAYS_28;
        }

        List<String> lables = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            lables.add(String.valueOf(i+1));
        }
        return lables;
    }

    private boolean isInArr(int[] arr, int value){
        return IntStream.of(arr).anyMatch(x -> x == value);
    }

    public List<String> getMonthes(){
        return Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
    }

    public List<String> getHours(List<ISensorEntity> records){
        List<String> labels = new ArrayList<>();
        for (ISensorEntity entity : records) {
            labels.add(dateTimeToTime(entity.getDate()));
        }
        return labels;
    }

    private String dateTimeToTime(LocalDateTime dateTime){
        int hour = dateTime.getHour();
        int minutes = dateTime.getMinute();
        LocalTime time = LocalTime.of(hour, minutes);
        return parseTimeToString(time);
    }

    private String parseTimeToString (LocalTime time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

}
