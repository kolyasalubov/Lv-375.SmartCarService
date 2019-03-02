package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.NewCarDTO;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.exceptions.CarRegisteredAlreadyExсeption;
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

    public List<CarDto> findAll() {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : carRepository.findAll()) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    public List<CarDto> findByUserId(Long id) {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : carRepository.findByUserId(id)) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    public CarDto getCarById(Long id) {
        Car car = carRepository.getCarById(id);
        CarDto carDto;
        if(car == null){
            carDto = null;
            return carDto;
        } else {
            carDto = getCarDto(car);
            return carDto;
        }
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public CarDto findByVin(String vin) {
        Car car = carRepository.findByVin(vin);
        CarDto carDto;
        if(car == null){
            carDto = null;
            return carDto;
        } else {
            carDto = getCarDto(car);
            return carDto;
        }
    }

    public CarDto findByNumber(String number) {
        Car car = carRepository.findByNumber(number);
        CarDto carDto;
        if(car == null){
            carDto = null;
            return carDto;
        } else {
            carDto = getCarDto(car);
            return carDto;
        }
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
        List<CarDto>carDtos=new ArrayList<>();

        for (Car car:carRepository.findAll()){
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    @Override
    public void createByDealer(CarDto carDto, String username) {
 carRepository.save(new Car(carDto.getBrand(),carDto.getModel(),carDto.getGraduation_year(),carDto.getNumber(),carDto.getPrice(),carDto.getVin(),dealerRepository.findByUserEntity_Username(username)));
    }

    @Override
    public List<CarDto> findbyUserLogin(String login) {
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : carRepository.findAllByDealer(dealerRepository.findByUserEntity_Username(login))) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

}
