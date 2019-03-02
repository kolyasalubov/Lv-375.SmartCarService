package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.NewCarDTO;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.exceptions.CarNotFoundException;
import ua.ita.smartcarservice.exceptions.CarRegisteredAlreadyExсeption;
import ua.ita.smartcarservice.exceptions.CarsNotFoundException;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.sales.DealerEntityRepository;
import ua.ita.smartcarservice.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DealerEntityRepository dealerRepository;

    /* Create car*/
    @Override
    public void addCar(NewCarDTO newCarDTO, String username) {
        UserEntity carOwner = this.userRepository.findByUsername(username).get();

        if (carRepository.findByNumber(newCarDTO.getNumber()) == null && carRepository.findByVin(newCarDTO.getVin()) == null) {
            Car car = new Car();
            car.setBrand(newCarDTO.getBrand());
            car.setModel(newCarDTO.getModel());
            car.setGraduation_year(newCarDTO.getGraduation_year());
            car.setNumber(newCarDTO.getNumber());
            car.setVin(newCarDTO.getVin());
            car.setUser(carOwner);

            carRepository.save(car);
        } else {
            throw new CarRegisteredAlreadyExсeption();
        }
    }

    /* Find all cars*/
    @Override
    public List<CarDto> findAll() {
        List<Car> cars = carRepository.findAll();
        if (cars.isEmpty()) {
            throw new CarsNotFoundException();
        }
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : carRepository.findAll()) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    /* Find cars by user id*/
    @Override
    public List<CarDto> findByUserId(Long id) {
        List <Car> cars = carRepository.findByUserId(id);
        if (cars.isEmpty()) {
            throw new CarsNotFoundException();
        }
        List<CarDto> carDtos = new ArrayList<>();
            for (Car car : carRepository.findByUserId(id)) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    /* Find cars by username */
    @Override
    public List<CarDto> findByUsername(String username){
        List <Car> cars = carRepository.findByUsername(username);
        if (cars.isEmpty()) {
            throw new CarsNotFoundException();
        }
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : carRepository.findByUsername(username)) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    /* Find car by id*/
    @Override
    public CarDto findCarById(Long id) {
        Car car = carRepository.getCarById(id);
        if (car == null) {
            throw new CarNotFoundException(id);
        }
        CarDto carDto;
        if(car == null){
            carDto = null;
            return carDto;
        } else {
            carDto = getCarDto(car);
            return carDto;
        }
    }

    /* Find car by vin*/
    @Override
    public CarDto findByVin(String vin) {
        Car car = carRepository.findByVin(vin);
        if (car == null) {
            throw new CarNotFoundException(vin);
        }
        CarDto carDto;
        if(car == null){
            carDto = null;
            return carDto;
        } else {
            carDto = getCarDto(car);
            return carDto;
        }
    }

    /* Find car by number*/
    @Override
    public CarDto findByNumber(String number) {
        Car car = carRepository.findByNumber(number);
        if (car == null) {
            throw new CarNotFoundException(number);
        }
        CarDto carDto;
        if(car == null){
            carDto = null;
            return carDto;
        } else {
            carDto = getCarDto(car);
            return carDto;
        }
    }

    /* Delete car by id*/
    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
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
                car.getUser(),
                car.getVehicleInspections());

        return carDto;
    }

    //for CarDto => CarDto
    public Car getCar(CarDto carDto) {
        Car car = new Car(
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getGraduation_year(),
                carDto.getNumber(),
                carDto.getVin(),
                carDto.getCarOwner());
        return car;
    }

    @Override
    public List<CarDto> findAllDealersCars() {
        List<CarDto>carDtos=new ArrayList<>();

        List<Car>carList=carRepository.findAll();

        for(Car car:carList){

            if(car.getUser()==null ){
                carDtos.add(getCarDto(car));
            }

        }

        return carDtos;
    }

    @Override
    public List<CarDto> findByDealerEdr(String edr) {
        List<CarDto> carDtos=new ArrayList<>();

        for (Car car:carRepository.findAll()){
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    public void createByDealer(String brand, String model, String graduation_year, String number,Double price, String vin, String username) {

        Car car=new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setGraduation_year(graduation_year);
        car.setNumber(number);
        car.setPrice(price);
        car.setVin(vin);
        car.setDealer(dealerRepository.findByUserEntity_Username(username));
        carRepository.save(car);
    }

    @Override
    public List<CarDto> findbyUserLogin(String login) {
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : carRepository.findAllByDealer(dealerRepository.findByUserEntity_Username(login))) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    //for used car
    public void create(String brand, String model, String graduation_year, String number, String vin, String username) {
        UserEntity carOwner = this.userRepository.findByUsername(username).get();

        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setGraduation_year(graduation_year);
        car.setNumber(number);
        car.setVin(vin);
        car.setUser(carOwner);

        carRepository.save(car);
    }

}
