package ua.ita.smartcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.service.impl.CarServiceImpl;
import ua.ita.smartcarservice.service.impl.UserServiceImpl;

import java.util.List;


@RestController
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private UserServiceImpl userService;

    //create used car
    @PostMapping("/ucar")
    public ResponseEntity createCar(@RequestParam(value = "brand") String brand,
                                    @RequestParam(value = "model") String model,
                                    @RequestParam(value = "graduation_year") String graduation_year,
                                    @RequestParam(value = "number") String number,
                                    @RequestParam(value = "vin") String vin) {

//TODO take user from token and add to car
        UserEntity carOwner = new UserEntity("tod@gmail.com", "tod", "tod", "tod", "235688");
        Car car = new Car(brand, model, graduation_year, number, vin, carOwner);
        carService.create(car);

        return new ResponseEntity(HttpStatus.CREATED);
    }


    @DeleteMapping("/car/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            List<CarDto> cars = carService.findAll();
            for (CarDto c : cars) {
                if (c.getId() == id) {
                    carService.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        CarDto car;
        try {
            car = carService.getCarById(id);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/cars/all")
    public ResponseEntity<List<CarDto>> findAll() {
        List<CarDto> cars = carService.findAll();
        if (cars.isEmpty()) {
            return new ResponseEntity<>(cars, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    //TODO Implement get user from token
    @GetMapping("/ownercars/{id}")
    public ResponseEntity<List<CarDto>> findByUserId(@PathVariable Long id) {
         List<CarDto> cars = carService.findByUserId(id);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    @GetMapping("/carbyvin/{vin}")
    public ResponseEntity<CarDto> findByVin(@PathVariable String vin) {
        CarDto car;
        try {
            car = carService.findByVin(vin);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

}
