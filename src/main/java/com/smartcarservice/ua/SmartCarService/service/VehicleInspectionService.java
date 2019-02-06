package com.smartcarservice.ua.SmartCarService.service;

import java.util.List;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.VehicleInspectionDto;
import com.smartcarservice.ua.SmartCarService.entity.car.Car;

public interface VehicleInspectionService {
	
	void saveVehicleInspection (VehicleInspectionDto vehicleInspectionDto);

	List<VehicleInspectionDto> getCarsForYearlyInspection ();
	
	VehicleInspectionDto getVehicleInspection(long id);

//	VehicleInspection getLastVehicleInspection(long carId);
	
	List<Car> getCarsForVehicleInspectionByMileage();
}
