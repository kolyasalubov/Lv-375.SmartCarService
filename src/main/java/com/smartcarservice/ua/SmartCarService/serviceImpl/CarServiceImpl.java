package com.smartcarservice.ua.SmartCarService.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.CarDto;
import com.smartcarservice.ua.SmartCarService.entity.car.CarOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.repository.CarRepository;
import com.smartcarservice.ua.SmartCarService.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    //for used car
    public void create (Car car){
        carRepository.save(car);
    }

    public List<CarDto> findAll() {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : carRepository.findAll()) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    public List<CarDto> findByCarOwnerId(Long id) {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : carRepository.findByCarOwnerId(id)) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    public CarDto getCarById(Long id) {
        Car car = carRepository.getCarById(id);
        CarDto carDto = getCarDto(car);
        return carDto;
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public CarDto findByVin(String vin) {
        Car car = carRepository.findByVin(vin);
        CarDto carDto = getCarDto(car);
        return carDto;
    }

    //for Car => CarDto
    public CarDto getCarDto(Car car) {
        CarDto carDto = new CarDto(car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getGraduation_year(),
                car.getNumber(),
                car.getPrice(),
                car.getVin(),
                car.getEnd_guarantee(),
                car.getDealer(),
                car.getCarOwner(),
                car.getVehicleInspections());

        return carDto;
    }
}
