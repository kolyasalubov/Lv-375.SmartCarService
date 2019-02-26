package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class NewBookingDto {

    String start;

    Long requiredTime;

    List<String> workerId;

    Long carId;

}
