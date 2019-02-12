package ua.ita.smartcarservice.controller.alerts;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.dto.alerts.VehicleInspectionDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.service.alerts.NotificationService;
import ua.ita.smartcarservice.service.alerts.VehicleInspectionService;


@RestController
public class VehicleInspectionScheduleController {
	private final String yearlyInspection = ": It's time for yearly inspection. Last inspection was on: ";
	private final String mileageInspection = ": You have ridden more than 10 km since "
			+"your last overal inspection";

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
					yearlyInspection
					+ String.valueOf(vehicleInspectionService.getDateOfLastVehicleInspection(v.getCar().getId())),
					new Timestamp(System.currentTimeMillis()),
					v.getCar().getId(),
					v.getCar().getUser().getId(),
					7l
			));
		}
		//users to warn by car mileage
		List<Car> inspectionsMileage = 
				vehicleInspectionService.getCarsForVehicleInspectionByMileage();
		System.out.println("****inspectionsMileage size: " + inspectionsMileage.size());
		for (Car c : inspectionsMileage) {
			toSave.add(new NotificationsDto(
					c.getBrand() + " " + c.getModel() +
					mileageInspection,
					new Timestamp(System.currentTimeMillis()),
					c.getId(),
					c.getUser().getId(),
					7l
			));
		}
		System.out.println(toSave.size());
		for (NotificationsDto n : toSave) {
			System.out.println(n.toString());
		}
		if (!toSave.isEmpty()) {
			notificationService.saveAllNotifications(toSave);
		}
	}
}
