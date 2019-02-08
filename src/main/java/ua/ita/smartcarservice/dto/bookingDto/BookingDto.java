package ua.ita.smartcarservice.dto.bookingDto;

import lombok.Data;

import java.util.List;

@Data
public class BookingDto {

    List<String> workerId;

    String time;

    String numberOfDay;

    String needTime;

}