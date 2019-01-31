package com.smartcarservice.ua.SmartCarService.ServiceImpl.sensors.alert;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartcarservice.ua.SmartCarService.Repository.sensors.alert.VehicleInspectionRepository;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.VehicleInspection;
import com.smartcarservice.ua.SmartCarService.service.sensors.alert.VehicleInspectionService;


public class VehicleInspectionImpl implements VehicleInspectionService{

	@Autowired
	private VehicleInspectionRepository vehicleInspectionRepository;
	
	@Override
	public VehicleInspection getLastVehicleInspection(long carId) {
		return vehicleInspectionRepository.getLastVehicleInspections(carId);
	}

	@Override
	public void saveInspection(VehicleInspection vehicleInspection) {
		vehicleInspectionRepository.save(vehicleInspection);	
	}

	
}
