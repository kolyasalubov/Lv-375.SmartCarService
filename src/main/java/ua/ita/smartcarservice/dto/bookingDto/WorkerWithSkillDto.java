package ua.ita.smartcarservice.dto.bookingDto;

import lombok.Data;

import java.util.List;

@Data
public class WorkerWithSkillDto {

    private List<String> name;

    private Long stoId;

}