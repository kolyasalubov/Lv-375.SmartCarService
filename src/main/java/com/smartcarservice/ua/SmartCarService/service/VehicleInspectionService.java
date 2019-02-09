package com.smartcarservice.ua.SmartCarService.service;

import java.util.Date;
import java.util.List;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.VehicleInspectionDto;
import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.VehicleInspection;

public interface VehicleInspectionService {
	
	void saveVehicleInspection (VehicleInspectionDto vehicleInspectionDto);

	List<VehicleInspectionDto> getCarsForYearlyInspection ();
	
	VehicleInspectionDto getVehicleInspection(long id);

	VehicleInspection getLastVehicleInspection(long carId);
	
	Date getDateOfLastVehicleInspection(long carId);
	
	List<Car> getCarsForVehicleInspectionByMileage();
}
