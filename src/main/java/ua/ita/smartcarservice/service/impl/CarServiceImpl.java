package ua.ita.smartcarservice.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


import ua.ita.smartcarservice.dto.stoDto.CarDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.DealerRepository;
import ua.ita.smartcarservice.service.CarService;

import javax.xml.crypto.Data;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    DealerRepository dealerRepository;

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

    @Override
    public Boolean isCarGuarantee(Car car) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date =  ft.parse(ft.format(new Date()));
        if(ft.parse(ft.format(car.getEnd_guarantee())).compareTo(date)==1 || ft.parse(ft.format(car.getEnd_guarantee())).compareTo(date)==0) {
            return true;
        }else
        return false;
    }

    @Override
    public void updateCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> allGuaranteeCarinDealer(Dealer dealer) throws ParseException {
        List<Car>guaranteeCar=new ArrayList<>();

        List<Car>cars=carRepository.getAllByDealer(dealer);

        for (int i=0;i<cars.size();i++){
            if (isCarGuarantee(cars.get(i))){
                guaranteeCar.add(cars.get(i));
            }
        }
        return guaranteeCar;
    }

    @Override
    public Car findCarByVin(String vin) {
        return carRepository.findByVin(vin);
    }

    @Override
    public List<Car> dealerCars(Long id) {
        Dealer dealer = dealerRepository.getDealerById(id);
        return  carRepository.getAllByDealer(dealer);
    }
}
