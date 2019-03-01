package ua.ita.smartcarservice.dto.alerts;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.ita.smartcarservice.dto.CarDto;

@Getter
@Setter
@NoArgsConstructor
public class NotificationsDto {

	private Long id;
	private String message;
	private String suggestion;
	private Timestamp notificationTime;
	private String type;
	private Boolean isVisible;
	private Long carId;
	private Long userId;
	private Long workTypeId;

	/* to save Notification in method getCarsForYearlyInspetion */
	public NotificationsDto(AlertsDto alertsDto, VehicleInspectionDto vehicleInspection) {
		this.message = vehicleInspection.getCar().getBrand() + " "
				+ vehicleInspection.getCar().getModel() + ": "
				+ alertsDto.getDescription();
		this.suggestion = alertsDto.getSuggestion();
		this.notificationTime = new Timestamp(System.currentTimeMillis());
		this.type = alertsDto.getAlertType();
		this.isVisible = true;
		this.carId = vehicleInspection.getCar().getId();
		this.userId = vehicleInspection.getCar().getUser().getId();
		this.workTypeId = alertsDto.getWorkType().getWorkId();
	}

	/* to save notification for user */
	public NotificationsDto(AlertsDto alertsDto, CarDto car) {
		this.message = car.getBrand() + " " + car.getModel() + ": " + alertsDto.getDescription();
		this.suggestion = alertsDto.getSuggestion();
		this.notificationTime = new Timestamp(System.currentTimeMillis());
		this.type = alertsDto.getAlertType();
		this.isVisible = true;
		this.carId = car.getId();
		this.userId = car.getCarOwner().getId();
		this.workTypeId = alertsDto.getWorkType() == null ? null : alertsDto.getWorkType().getWorkId();
	}

	public NotificationsDto(Long id, String message, String suggestion, Timestamp notificationTime,
							String type, Boolean isVisible, Long carId, Long userId, Long workTypeId) {
		this.id = id;
		this.message = message;
		this.suggestion = suggestion;
		this.notificationTime = notificationTime;
		this.type = type;
		this.isVisible = isVisible;
		this.carId = carId;
		this.userId = userId;
		this.workTypeId = workTypeId;
	}
}
