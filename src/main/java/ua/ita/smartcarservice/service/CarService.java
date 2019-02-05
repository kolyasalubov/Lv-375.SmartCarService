package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.stoDto.CarDto;
import ua.ita.smartcarservice.entity.car.Car;

import java.util.List;

public interface CarService {

    List<CarDto> findByCarOwnerId(Long id);

    CarDto findByVin(String vin);

    CarDto getCarById(Long id);

    List<Car> dealerCars(Long id);

}
