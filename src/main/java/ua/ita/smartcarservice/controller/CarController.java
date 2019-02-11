package ua.ita.smartcarservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.service.impl.CarServiceImpl;
import ua.ita.smartcarservice.service.impl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
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
                                    @RequestParam(value = "vin") String vin,
                                    @RequestParam(value = "username") String username){

        UserDto carOwner = this.userService.findByUsername(username);
        UserEntity currentUser = new UserEntity (carOwner.getUsername(), carOwner.getPassword(),carOwner.getEmail(), carOwner.getFullName(), carOwner.getNumberPhone());
        CarDto car = new CarDto(brand, model, graduation_year, number, vin, currentUser);
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
