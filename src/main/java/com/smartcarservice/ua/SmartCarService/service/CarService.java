package com.smartcarservice.ua.SmartCarService.service;


import com.smartcarservice.ua.SmartCarService.dto.stoDto.CarDto;
import com.smartcarservice.ua.SmartCarService.entity.car.Car;


import java.util.List;

public interface CarService {

    List<CarDto> findByCarOwnerId(Long id);

    CarDto findByVin(String vin);

    CarDto getCarById(Long id);

    List<Car> dealerCars(Long id);

}