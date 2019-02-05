package ua.ita.smartcarservice.dto.stoDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.entity.sto.TechnicalManager;
import ua.ita.smartcarservice.entity.sto.Worker;
import lombok.Data;

import java.util.Set;

@Data
public class TechnicalServiceDto {

    private Long stoId;

    private String name;

    private String address;

    private Dealer dealer;


    private TechnicalManager technicalManager;

    @JsonIgnore//Works
    private Set<Worker> workerSet;
}
