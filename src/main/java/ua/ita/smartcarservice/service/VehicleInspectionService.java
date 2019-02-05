package ua.ita.smartcarservice.service;

import java.util.List;

import ua.ita.smartcarservice.dto.stoDto.VehicleInspectionDto;

public interface VehicleInspectionService {
	
	void saveVehicleInspection (VehicleInspectionDto vehicleInspectionDto);

	List<VehicleInspectionDto> getCarsForYearlyInspection ();
	
	VehicleInspectionDto getVehicleInspection(long id);

//	VehicleInspection getLastVehicleInspection(long carId);
	
}
