package com.smartcarservice.ua.SmartCarService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.VehicleInspection;
import com.smartcarservice.ua.SmartCarService.repository.VehicleInspectionRepository;
import com.smartcarservice.ua.SmartCarService.service.VehicleInspectionService;


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
