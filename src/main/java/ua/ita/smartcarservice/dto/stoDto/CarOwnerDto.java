package ua.ita.smartcarservice.dto.stoDto;

import lombok.Data;

import ua.ita.smartcarservice.entity.car.Car;

import java.util.Set;

@Data
public class CarOwnerDto {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String userName;

    Set<Car> cars;

    public CarOwnerDto() {
    }
    
    public CarOwnerDto(Long id, String email, String password, String fullName, String userName, Set<Car> cars) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.userName = userName;
        this.cars = cars;
    }

    public CarOwnerDto(Long id, String email, String password, String fullName, String userName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.userName = userName;
    }

 }
