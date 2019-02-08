package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.stoDto.CarDto;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sales.Dealer;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface CarService {

    List<CarDto> findByCarOwnerId(Long id);

    CarDto findByVin(String vin);

    CarDto getCarById(Long id);

    List<Car> dealerCars(Long id);

    Car findCarByVin(String vin);

    void updateCar(Car car);

    List<Car> allGuaranteeCarinDealer(Dealer dealer) throws ParseException;

    Boolean isCarGuarantee(Car car) throws ParseException;

}
