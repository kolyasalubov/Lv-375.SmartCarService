package ua.ita.smartcarservice.dto.technicalservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.dto.sales.DealerDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.sales.DealerEntity;

import java.util.Set;

@Data
public class TechnicalServiceDto {

    private Long stoId;

    private String name;

    private String address;



    private UserEntity technicalManager;

    @JsonIgnore//Works
    private Set<UserEntity> workerSet;
}
