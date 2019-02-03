package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.VehicleInspection;

public interface VehicleInspectionService {
	
	void saveInspection(VehicleInspection vehicleInspection);

	VehicleInspection getLastVehicleInspection(long carId);
}
