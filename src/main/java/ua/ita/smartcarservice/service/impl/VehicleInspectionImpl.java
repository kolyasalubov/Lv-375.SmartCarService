package ua.ita.smartcarservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.ita.smartcarservice.dto.stoDto.VehicleInspectionDto;
import ua.ita.smartcarservice.entity.sensors.alert.VehicleInspection;
import ua.ita.smartcarservice.repository.VehicleInspectionRepository;
import ua.ita.smartcarservice.service.VehicleInspectionService;


public class VehicleInspectionImpl implements VehicleInspectionService{

	@Autowired
	private VehicleInspectionRepository vehicleInspectionRepository;
	
	@Override
	public void saveVehicleInspection(VehicleInspectionDto vehicleInspectionDto) {
		VehicleInspection entity = new VehicleInspection(
				vehicleInspectionDto.getId(),
				vehicleInspectionDto.getDateOfInspection(),
				vehicleInspectionDto.getMileageOfCar(), 
				vehicleInspectionDto.getCar());
		vehicleInspectionRepository.save(entity);
	}

	@Override
	public List<VehicleInspectionDto> getCarsForYearlyInspection() {
		List<VehicleInspectionDto> toReturn = new ArrayList();
		for (VehicleInspection v : vehicleInspectionRepository.getCarsForYearlyInspection()) {
			toReturn.add(new VehicleInspectionDto(v));
		}
		return toReturn;
	}

	@Override
	public VehicleInspectionDto getVehicleInspection(long id) {
		VehicleInspection entity  = vehicleInspectionRepository.getOne(id);
		VehicleInspectionDto dto = new VehicleInspectionDto(entity.getId(), entity.getDateOfInspection(), entity.getMileageOfCar(), entity.getCar());
		return dto;
	}

	
}
