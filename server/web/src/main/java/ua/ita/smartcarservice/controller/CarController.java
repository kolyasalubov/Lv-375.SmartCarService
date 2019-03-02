package ua.ita.smartcarservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.ita.smartcarservice.dto.NewCarDTO;
import ua.ita.smartcarservice.exceptions.CarRegisteredAlreadyExсeption;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.exceptions.CarNotFoundException;
import ua.ita.smartcarservice.exceptions.CarsNotFoundException;
import ua.ita.smartcarservice.service.CarService;


@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    /* Delete car by id*/
    @DeleteMapping("/car/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        carService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Find car by id*/
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> findCarById(@PathVariable Long id) {
        CarDto car = carService.findCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /* Find all cars */
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> findAll() {
        List<CarDto> cars = carService.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /* Find owner's cars by user id*/
    @GetMapping("/owner/{id}/cars")
    public ResponseEntity<List<CarDto>> findByUserId(@PathVariable Long id) {
        List<CarDto> cars = carService.findByUserId(id);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /* Find owner's cars by user id*/
    @GetMapping("/owner/{username}/car")
    public ResponseEntity<List<CarDto>> findByUsername(@PathVariable String username) {
        List<CarDto> cars = carService.findByUsername(username);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /*  Find car by vin */
    @GetMapping("/car/{vin}/vin")
    public ResponseEntity<CarDto> findByVin(@PathVariable String vin) {
        CarDto car = carService.findByVin(vin);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /*  Find car by number */
    @GetMapping("/car/{number}/number")
    public ResponseEntity<CarDto> findByNumber(@PathVariable String number) {
        CarDto car = carService.findByNumber(number);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /* Create car */
    @PostMapping("/newcar")
    public ResponseEntity addCar(@RequestParam(value = "username") String username,
                                 @RequestBody NewCarDTO newCarDTO) {
        carService.addCar(newCarDTO, username);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /* Create car */
    @PostMapping("/car")
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
