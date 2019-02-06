package com.smartcarservice.ua.SmartCarService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.CarDto;
import com.smartcarservice.ua.SmartCarService.dto.stoDto.VehicleInspectionDto;
import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.car.CarOwner;
import com.smartcarservice.ua.SmartCarService.service.VehicleInspectionService;

@RestController
public class VehicleInspectionScheduleController {

	VehicleInspectionService vehicleInspectionService;

	@Autowired
	public VehicleInspectionScheduleController(VehicleInspectionService vehicleInspectionService) {
		this.vehicleInspectionService = vehicleInspectionService;
	}

	@Scheduled(cron = "0 0 8 * * *", zone="Europe/Athens")
	public void getCarsForYearlyInspection() {
		//users to warn by date
		List<VehicleInspectionDto> inspections = 
				vehicleInspectionService.getCarsForYearlyInspection();
		List<CarOwner> usersToWarnByDate = new ArrayList<>();
		for (VehicleInspectionDto v : inspections) {
			CarOwner owner = v.getCar().getCarOwner();
			usersToWarnByDate.add(owner);
		}
		for (CarOwner owner : usersToWarnByDate) {
			//how to send message to all users
		}
		//users to warn by car mileage
		List<Car> inspectionsMileage = 
				vehicleInspectionService.getCarsForVehicleInspectionByMileage();
		List<CarOwner> usersToWarnByMileage = new ArrayList<>();
		for (Car c : inspectionsMileage) {
			CarOwner owner = c.getCarOwner();
			usersToWarnByMileage.add(owner);
		}
	}
	
}
