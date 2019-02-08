package ua.ita.smartcarservice.service.impl;

import ua.ita.smartcarservice.entity.sensors.data.ISensorEntity;
import ua.ita.smartcarservice.entity.sensors.data.TirePressureEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

class LabelsProvider {

    List<String> getMonths(List<Object[]> records){
        List<String> names = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        List<String> lables = new ArrayList<>();
        for(Object[] el : records){
            lables.add(names.get((int)el[0] - 1));
        }
        return lables;
    }

    List<String> getDays(List<Object[]> records){
        List<String> lables = new ArrayList<>();
        for(Object[] el : records){
            lables.add(String.valueOf(el[0]));
        }
        return lables;
    }

    List<String> getHours(List<ISensorEntity> records){
        List<String> labels = new ArrayList<>();
        for (ISensorEntity entity : records) {
            labels.add(dateTimeToTime(entity.getDate()));
        }
        return labels;
    }

    List<String> getTireHours(List<TirePressureEntity> records){
        List<String> labels = new ArrayList<>();
        for (TirePressureEntity entity : records) {
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
