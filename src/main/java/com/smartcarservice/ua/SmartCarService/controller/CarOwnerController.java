package com.smartcarservice.ua.SmartCarService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartcarservice.ua.SmartCarService.entity.car.CarOwner;
import com.smartcarservice.ua.SmartCarService.serviceImpl.CarOwnerServiceImpl;

@RestController
public class CarOwnerController {
	
	@Autowired
	private CarOwnerServiceImpl carOwnerService;

	
	@PostMapping ("/carowner")
	public void addCarOwner (@RequestBody CarOwner carOwner) {
		carOwnerService.create(carOwner);
	}
	
	@DeleteMapping("/carowner")
	public void  deleteCarOwner (@RequestParam Long id) {
		carOwnerService.deleteById(id);
	}
	
	@GetMapping ("/carowner")
	public CarOwner findById (@RequestParam Long id){
		CarOwner carOwner = carOwnerService.findById(id);
		return carOwner;
	}
	
	@GetMapping ("/carowners")
	public List<CarOwner> findAll (){
		List<CarOwner> carOwners = carOwnerService.findAll();
		return carOwners;
	}
	
	@GetMapping ("/carownerbyusername")
	public CarOwner findByUserName (String userName) {
		CarOwner carOwner = carOwnerService.findByUserName(userName);
		return carOwner;
	}
	
//reset pass?

}
