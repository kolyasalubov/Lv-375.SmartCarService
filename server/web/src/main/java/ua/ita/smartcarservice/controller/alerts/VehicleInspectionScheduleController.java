package ua.ita.smartcarservice.controller.alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.dto.alerts.VehicleInspectionDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.service.alerts.NotificationService;
import ua.ita.smartcarservice.service.alerts.VehicleInspectionService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class VehicleInspectionScheduleController {
	private final String yearlyInspection = ": It's time for yearly inspection. ";
	private final String mileageInspection = ": You have ridden more than 10 km since "
			+"your last overal inspection";
	private final Long vehicleInspector = new Long(7);
	
	VehicleInspectionService vehicleInspectionService;
	NotificationService notificationService;

	@Autowired
	public VehicleInspectionScheduleController(VehicleInspectionService vehicleInspectionService,
			NotificationService notificationService) {
		this.vehicleInspectionService = vehicleInspectionService;
		this.notificationService = notificationService;
	}
	@Scheduled(cron = "0 0 8 * * *", zone="Europe/Athens")
	public void getCarsForYearlyInspection() {
		List<NotificationsDto> toSave = new ArrayList<>();
		//cars for yearly inspection
		List<VehicleInspectionDto> viDto =
				vehicleInspectionService.getCarsForYearlyInspection();
		for (VehicleInspectionDto v : viDto) {
			toSave.add(new NotificationsDto(
					v.getCar().getBrand() + " " + v.getCar().getModel() +
					yearlyInspection,
					new Timestamp(System.currentTimeMillis()),
					v.getCar().getId(),
					v.getCar().getUser().getId(),
					vehicleInspector
			));
		}
		//users to warn by car mileage
		List<Car> inspectionsMileage =
				vehicleInspectionService.getCarsForVehicleInspectionByMileage();
		for (Car c : inspectionsMileage) {
			toSave.add(new NotificationsDto(
					c.getBrand() + " " + c.getModel() +
					mileageInspection,
					new Timestamp(System.currentTimeMillis()),
					c.getId(),
					c.getUser().getId(),
					vehicleInspector
			));
		}
		if (!toSave.isEmpty()) {
			notificationService.saveAllNotifications(toSave);
		}
	}

	@PostMapping("/inspection")
	public void createVehicleInspection (@RequestParam(value = "dateOfInspection") Date dateOfInspection,
										 @RequestParam(value = "mileageOfCar") Integer mileageOfCar,
										 @RequestParam(value = "vin") String vin){
		vehicleInspectionService.createVehicleInspection(dateOfInspection, mileageOfCar, vin);
	}
}
