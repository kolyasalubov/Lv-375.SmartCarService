package ua.ita.smartcarservice.dto.bookingDto;

import lombok.Data;

import java.util.List;

@Data
public class NewSessionDto {

    String starttime;

    String endtime;

    List<String> workerId;

    Long carId;

}
