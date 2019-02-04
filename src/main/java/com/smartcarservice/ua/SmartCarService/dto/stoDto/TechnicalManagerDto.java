package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import com.smartcarservice.ua.SmartCarService.entity.sto.Worker;
import lombok.Data;

import java.util.Set;

@Data
public class TechnicalManagerDto {

    private long id;

    private String email;

    private String password;

    private String fullName;

    private String userName;

    private TechnicalService technicalService;

    Set<Worker> workers;

    public TechnicalManagerDto(long id, String email, String password, String fullName, String userName, TechnicalService technicalService, Set<Worker> workers) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.userName = userName;
        this.technicalService = technicalService;
        this.workers = workers;
    }
}
