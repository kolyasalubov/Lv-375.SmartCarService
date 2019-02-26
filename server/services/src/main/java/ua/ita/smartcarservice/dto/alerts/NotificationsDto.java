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
	private Long skillId;

	/* to save Notification in method getCarsForYearlyInspetion */
	public NotificationsDto(FaultCodeDto faultCode, VehicleInspectionDto vehicleInspection) {
		this.message = vehicleInspection.getCar().getBrand() + " "
				+ vehicleInspection.getCar().getModel() + ": "
				+ faultCode.getDescription();
		this.suggestion = faultCode.getSuggestion();
		this.notificationTime = new Timestamp(System.currentTimeMillis());
		this.type = faultCode.getType();
		this.isVisible = true;
		this.carId = vehicleInspection.getCar().getId();
		this.userId = vehicleInspection.getCar().getUser().getId();
		this.skillId = faultCode.getSkill().getSkillId();
	}

	/* to save notification for user */
	public NotificationsDto(FaultCodeDto faultCode, CarDto car) {
		this.message = car.getBrand() + " " + car.getModel() + ": " + faultCode.getDescription();
		this.suggestion = faultCode.getSuggestion();
		this.notificationTime = new Timestamp(System.currentTimeMillis());
		this.type = faultCode.getType();
		this.isVisible = true;
		this.carId = car.getId();
		this.userId = car.getCarOwner().getId();
		this.skillId = faultCode.getSkill() == null ? null : faultCode.getSkill().getSkillId();
	}

	public NotificationsDto(Long id, String message, String suggestion, Timestamp notificationTime,
							String type, Boolean isVisible, Long carId, Long userId, Long skillId) {
		this.id = id;
		this.message = message;
		this.suggestion = suggestion;
		this.notificationTime = notificationTime;
		this.type = type;
		this.isVisible = isVisible;
		this.carId = carId;
		this.userId = userId;
		this.skillId = skillId;
	}
}
