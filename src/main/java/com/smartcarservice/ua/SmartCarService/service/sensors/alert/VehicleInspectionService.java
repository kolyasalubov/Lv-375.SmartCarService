package com.smartcarservice.ua.SmartCarService.service.sensors.alert;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.VehicleInspection;

public interface VehicleInspectionService {
	
	void saveInspection(VehicleInspection vehicleInspection);

	VehicleInspection getLastVehicleInspection(long carId);
}
