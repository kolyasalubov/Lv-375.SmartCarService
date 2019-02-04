package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.Worker;
import lombok.Data;

import java.util.List;
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
