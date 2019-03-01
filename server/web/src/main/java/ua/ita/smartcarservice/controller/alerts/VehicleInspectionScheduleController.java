package ua.ita.smartcarservice.controller.alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.alerts.FaultCodeDto;
import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.dto.alerts.VehicleInspectionDto;
import ua.ita.smartcarservice.service.alerts.FaultCodeService;
import ua.ita.smartcarservice.service.alerts.NotificationService;
import ua.ita.smartcarservice.service.alerts.VehicleInspectionService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@RestController
public class VehicleInspectionScheduleController {

	@Autowired
	private VehicleInspectionService vehicleInspectionService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private FaultCodeService faultCodeService;

	@PostMapping("/api/inspection")
	public void createVehicleInspection (@RequestParam(value = "dateOfInspection") Date dateOfInspection,
										 @RequestParam(value = "mileageOfCar") Integer mileageOfCar,
										 @RequestParam(value = "vin") String vin){
		vehicleInspectionService.createVehicleInspection(dateOfInspection, mileageOfCar, vin);
	}
}
