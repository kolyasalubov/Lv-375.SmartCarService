package ua.ita.smartcarservice.service.impl.alerts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.alerts.AlertsDto;
import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.dto.alerts.VehicleInspectionDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.alerts.Notifications;
import ua.ita.smartcarservice.entity.sensors.enums.SensorTypes;
import ua.ita.smartcarservice.repository.alerts.NotificationsRepository;
import ua.ita.smartcarservice.service.CarService;
import ua.ita.smartcarservice.service.SensorService;
import ua.ita.smartcarservice.service.alerts.AlertsService;
import ua.ita.smartcarservice.service.alerts.NotificationService;
import ua.ita.smartcarservice.service.alerts.VehicleInspectionService;

@Service
public class NotificationsServiceImpl implements NotificationService{

	private final NotificationsRepository notificationsRepository;

	private final SensorService sensorService;

	private final AlertsService alertsService;

	private final CarService carService;

	private final VehicleInspectionService vehicleInspectionService;

	@Autowired
	public NotificationsServiceImpl(NotificationsRepository notificationsRepository, SensorService sensorService, AlertsService alertsService, CarService carService, VehicleInspectionService vehicleInspectionService) {
		this.notificationsRepository = notificationsRepository;
		this.sensorService = sensorService;
		this.alertsService = alertsService;
		this.carService = carService;
		this.vehicleInspectionService = vehicleInspectionService;
	}

	/* CREATE */
	@Override
	public void saveNotification(NotificationsDto notificationDto) {
		notificationsRepository.save(dtoToEntity(notificationDto));
	}

	/* Method to analyze sensors data and save notification for user */
	@Async
	@Override
	public void addNewNotification(RecordDto recordDto) {
		String sensorType = recordDto.getSensorType();
		Double currentValue = (Double)recordDto.getValues().values().toArray()[0];
		if (analyzeSensorData(sensorType, currentValue)) {
			RecordDto previousRecord = sensorService.findRecordBeforeDate(recordDto);
			if (!analyzeSensorData(sensorType, previousRecord.getValue())) {
				AlertsDto code = alertsService.getAlert(sensorType);
				CarDto car = carService.findByVin(recordDto.getCarVin());
				saveNotification(new NotificationsDto(code, car));
			}
		}
	}

	/* Method for sending vehicle inspection notifications to users */
	@Scheduled(cron = "0 0 8 * * *", zone="Europe/Athens")
	public void getCarsForYearlyInspection() {
		List<NotificationsDto> toSave = new ArrayList<>();

		/* cars for yearly inspection */
		AlertsDto yearlyInspection = alertsService.getAlert("yearly-inspection");
		List<VehicleInspectionDto> viDto =
				vehicleInspectionService.getCarsForYearlyInspection();
		viDto.forEach(inspection -> toSave.add(new NotificationsDto(yearlyInspection, inspection)));

		/* users to warn by car mileage */
		AlertsDto mileageInspection = alertsService.getAlert("mileage-inspection");
		List<CarDto> inspectionsMileage =
				vehicleInspectionService.getCarsForVehicleInspectionByMileage();
		inspectionsMileage.forEach(car -> toSave.add(new NotificationsDto(mileageInspection, car)));

		if (!toSave.isEmpty()) {
			saveAllNotifications(toSave);
		}
	}

	@Override
	public void saveAllNotifications(List<NotificationsDto> notificationsList) {
		List<Notifications> entities = new ArrayList<>();
		notificationsList.forEach(dto -> entities.add(dtoToEntity(dto)));
		notificationsRepository.saveAll(entities);
	}

	/* READ */
	@Override
	public NotificationsDto getNotification(Long id) {
		return entityToDto(notificationsRepository.getOne(id));
	}

	@Override
	public List<NotificationsDto> getAllNotificationsForUser(Long userId) {
		List<NotificationsDto> dtos = new ArrayList<>();
		notificationsRepository.getAllNotificationsForUser(userId).forEach(entity ->
			dtos.add(entityToDto(entity)));
		return dtos;
	}

	@Override
	public NotificationsDto findLastNotificationByMessage(String message) {
		return entityToDto(notificationsRepository.findLastNotificationByMessage(message));
	}

	/* UPDATE */
	@Override
	public void disableNotification(NotificationsDto notificationDto) {
		notificationDto.setIsVisible(false);
		notificationsRepository.save(dtoToEntityWithId(notificationDto));
	}


	/* DELETE */
	@Override
	public void deleteNotificationById(Long id) {
		notificationsRepository.deleteById(id);
	}

	/* methods to convert DTO to entity and entity to DTO */
	private Notifications dtoToEntity(NotificationsDto dto) {
		return new Notifications(dto.getMessage(),
				dto.getSuggestion(),
				dto.getNotificationTime(),
				dto.getType(),
				dto.getIsVisible(),
				dto.getCarId(),
				dto.getUserId(),
				dto.getWorkTypeName()) ;
	}

	private NotificationsDto entityToDto (Notifications entity) {
		return new NotificationsDto(entity.getId(),
				entity.getMessage(),
				entity.getSuggestion(),
				entity.getNotificationTime(),
				entity.getType(),
				entity.getIsVisible(),
				entity.getCarId(),
				entity.getUserId(),
				entity.getWorkTypeName());
	}

	private Notifications dtoToEntityWithId(NotificationsDto dto) {
		return new Notifications(dto.getId(),
				dto.getMessage(),
				dto.getSuggestion(),
				dto.getNotificationTime(),
				dto.getType(),
				dto.getIsVisible(),
				dto.getCarId(),
				dto.getUserId(),
				dto.getWorkTypeName()) ;
	}

	/* Helper method to analyze sensors data */
	private boolean analyzeSensorData(String type, Double value) {
		if(type.equals(SensorTypes.FUEL.toString()) && value < 10) {
			return true;
		} else if(type.equals(SensorTypes.GLASS_WASHER_FLUID.toString()) && value < 0.5){
			return true;
		} else if(type.equals(SensorTypes.COOLANT.toString()) && value < 1) {
			return true;
		}
		return false;
	}
}
