package ua.ita.smartcarservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.dto.stoDto.CarDto;
import ua.ita.smartcarservice.service.impl.CarOwnerServiceImpl;
import ua.ita.smartcarservice.service.impl.CarServiceImpl;
import ua.ita.smartcarservice.entity.car.CarOwner;

@RestController
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private CarOwnerServiceImpl carOwnerService;


    @Autowired
    private DealerServiceImpl dealerService;
    //create used car
    @PostMapping("/ucar")
    public void createCar(@RequestParam(value = "brand") String brand,
                          @RequestParam(value = "model") String model,
                          @RequestParam(value = "graduation_year") String graduation_year,
                          @RequestParam(value = "number") String number,
                          @RequestParam(value = "vin") String vin) {

//TODO take user from token and add to car
        CarOwner carOwner = new CarOwner("bekky@gmail.com", "bekky", "Bekky", "Bekky");
        Car car = new Car(brand, model, graduation_year, number, vin, carOwner);

        carService.create(car);
    }


//create car by dealer
    @PostMapping("/ucarByDealer")
    public void createCar(@RequestParam(value = "brand") String brand,
                          @RequestParam(value = "model") String model,
                          @RequestParam(value = "graduation_year") String graduation_year,
                          @RequestParam(value = "number") String number,
                          @RequestParam(value = "vin") String vin,
                          @RequestParam(value = "dealerID")Long id) {

        Dealer dealer=dealerService.findById(id);

        Car car = new Car(brand, model, graduation_year, number, vin, dealer);

        carService.create(car);
    }



    @DeleteMapping("/car")
    public void deleteById(@RequestParam(value = "id") Long id) {
        carService.deleteById(id);
    }

    @GetMapping("/car")
    public CarDto getCarById(@RequestParam(value = "id") Long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/cars")
    public List<CarDto> findAll() {
        return carService.findAll();
    }



    @GetMapping("/dealersCar")
    public List<Car>dealersCars(@RequestParam(value = "id") Long id){
        return carService.dealerCars(id);
    }

    //TODO Implement get user from token
    @GetMapping("/userscars")
    public List<CarDto> findByCarOwnerId(@RequestParam(value = "id") Long id) {
        String token = "token";
        return carService.findByCarOwnerId(id);
    }

    //TODO check if vin exist
    @GetMapping("/carbyvin")
    public CarDto findByVin(@RequestParam(value = "vin") String vin) {
        return carService.findByVin(vin);
    }

}
