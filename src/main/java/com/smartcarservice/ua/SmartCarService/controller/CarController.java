package com.smartcarservice.ua.SmartCarService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.serviceImpl.CarServiceImpl;
import com.smartcarservice.ua.SmartCarService.serviceImpl.CarOwnerServiceImpl;

@RestController
public class CarController {
	
	@Autowired
	private CarServiceImpl carService;
	
	@Autowired
	private CarOwnerServiceImpl carOwnerService;
	
	//test the controller
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
		
	@PostMapping ("/newcar")
	public void addCar (@RequestBody Car car,
			@RequestParam String token) {
/*		CarOwner carOwner= carOwnerService.findByUserName(token.getUserName);
		Car newCar = new Car(car.getBrand(),car.getModel(), car.getGraduation_year(), car.getNumber(), car.getVin(), carOwner);
		*/
		carService.create(car);
	}
	
	@DeleteMapping("/car")
	public void  deleteById (@RequestParam Long id) {
		carService.deleteById(id);
	}
	
	@GetMapping ("/car")
	public Car findById (@RequestParam Long id){
		Car car = carService.findById(id);
		return car;
	}
	
	@GetMapping ("/cars")
	public List<Car> findAll (){
		List<Car> cars = carService.findAll();
		return cars;
	}
	
	@GetMapping ("/userscars")
	public List<Car> findByCarOwnerId (@RequestParam Long id){
		//TODO Implement get user from token
		String token = "token";
		List<Car> cars = carService.findByCarOwnerId(id);
		return cars;
	}
	
	//Check if vin exist
	@GetMapping("/carbyvin")
	public Car findByVin (@RequestParam String vin) {
		Car car = carService.findByVin(vin);
		return car;
	}

}
