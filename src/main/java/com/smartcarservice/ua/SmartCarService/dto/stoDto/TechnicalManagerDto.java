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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TechnicalService getTechnicalService() {
        return technicalService;
    }

    public void setTechnicalService(TechnicalService technicalService) {
        this.technicalService = technicalService;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }
}
