package ua.ita.smartcarservice.service.alerts;

import java.util.Date;
import java.util.List;

import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.alerts.VehicleInspectionDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.alerts.VehicleInspection;

public interface VehicleInspectionService {

	/* CREATE */
	void saveVehicleInspection (VehicleInspectionDto vehicleInspectionDto);

	/* READ */
	List<VehicleInspectionDto> getCarsForYearlyInspection ();

	VehicleInspectionDto getVehicleInspection(long id);

	Date getDateOfLastVehicleInspection(long carId);

	List<CarDto> getCarsForVehicleInspectionByMileage();

	void createVehicleInspection(java.sql.Date dateOfInspection, Integer mileageOfCar, String vin);
}
