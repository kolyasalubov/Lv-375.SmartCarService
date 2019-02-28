package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewBookingDto {

    String start;

    List<String> workerId;

    List<WorkInfoDto> workInfo = new ArrayList <>();

    Long carId;

}
