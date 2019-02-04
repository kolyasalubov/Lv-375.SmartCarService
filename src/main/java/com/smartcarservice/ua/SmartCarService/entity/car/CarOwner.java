package com.smartcarservice.ua.SmartCarService.entity.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartcarservice.ua.SmartCarService.entity.UserBaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "carowners")
public class CarOwner extends UserBaseEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "carOwner")
    private Set<Car> cars;

    public CarOwner() {
    }

    //BaseUser constructor
    public CarOwner(String email, String password, String fullName, String userName) {
        super(email, password, fullName, userName);

    }

    public CarOwner(String email, String password, String fullName, String userName, Set<Car> cars) {
        super(email, password, fullName, userName);
        this.cars = cars;
    }

}