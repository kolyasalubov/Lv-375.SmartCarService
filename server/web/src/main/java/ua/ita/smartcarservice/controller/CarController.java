package ua.ita.smartcarservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.ita.smartcarservice.controller.exceptions.CarRegisteredAlreadyExсeption;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.controller.exceptions.CarNotFoundException;
import ua.ita.smartcarservice.controller.exceptions.CarsNotFoundException;
import ua.ita.smartcarservice.service.CarService;


@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    /*   Delete car by id*/
    @DeleteMapping("/car/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        carService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Get car by id*/
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        CarDto car = carService.getCarById(id);
        if (carService.getCarById(id) == null) {
            throw new CarNotFoundException(id);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /* Get all cars */
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> findAll() {
        List<CarDto> cars = carService.findAll();
        if (cars.isEmpty()) {
            throw new CarsNotFoundException();
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    /* Get owner's cars */
    @GetMapping("/owner/{id}/cars")
    public ResponseEntity<List<CarDto>> findByUserId(@PathVariable Long id) {
        List<CarDto> cars = carService.findByUserId(id);
        if (cars.isEmpty()) {
            throw new CarsNotFoundException();
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /*  Get car by vin */
    @GetMapping("/car/{vin}/vin")
    public ResponseEntity<CarDto> findByVin(@PathVariable String vin) {
        CarDto car = carService.findByVin(vin);
        if (car == null) {
            throw new CarNotFoundException(vin);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /*  Get car by number */
    @GetMapping("/car/{number}/number")
    public ResponseEntity<CarDto> findByNumber(@PathVariable String number) {
        CarDto car = carService.findByNumber(number);
        if (car == null) {
            throw new CarNotFoundException(number);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /* Create car */
    @PutMapping("/car")
    public ResponseEntity createCar(@RequestParam(value = "brand") String brand,
                                    @RequestParam(value = "model") String model,
                                    @RequestParam(value = "graduationyear") String graduation_year,
                                    @RequestParam(value = "number") String number,
                                    @RequestParam(value = "vin") String vin,
                                    @RequestParam(value = "username") String username) {

        if (carService.findByNumber(number) == null && carService.findByVin(vin) == null) {
            carService.create(brand, model, graduation_year, number, vin, username);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw new CarRegisteredAlreadyExсeption();
        }
    }
}
