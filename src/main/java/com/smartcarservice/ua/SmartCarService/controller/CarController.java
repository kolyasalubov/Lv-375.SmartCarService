package com.smartcarservice.ua.SmartCarService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.serviceImpl.CarServiceImpl;
import com.smartcarservice.ua.SmartCarService.serviceImpl.CarOwnerServiceImpl;

@RestController
public class CarController {
	
	@Autowired
	private CarServiceImpl carService;
	
	//test the controller
	@GetMapping ("/")
	public String hello (){
		String hello = "Hello";
		return hello;
	}
	
	
	@PostMapping ("/car")
	public void addCar (@RequestBody Car car) {
		carService.create(car);
	}
	
	@DeleteMapping("/car")
	public void  deleteCar (@RequestParam Long id) {
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
