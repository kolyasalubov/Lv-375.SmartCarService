package ua.ita.smartcarservice.dto.alerts;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationsDto {

	private Long id;
	private String message;
	private Timestamp notificationTime;
	private String type;
	private Boolean isVisible;
	private Long carId;
	private Long userId;
	private Long skillId;
	
	//to save Notification
	public NotificationsDto(String message, Timestamp notificationTime, String type, Boolean isVisible,
			Long carId, Long userId, Long skillId) {
		this.message = message;
		this.notificationTime = notificationTime;
		this.type = type;
		this.isVisible = isVisible;
		this.carId = carId;
		this.userId = userId;
		this.skillId = skillId;
	}

	public NotificationsDto(Long id, String message, Timestamp notificationTime, String type, Boolean isVisible,
			Long carId, Long userId, Long skillId) {
		this.id = id;
		this.message = message;
		this.notificationTime = notificationTime;
		this.type = type;
		this.isVisible = isVisible;
		this.carId = carId;
		this.userId = userId;
		this.skillId = skillId;
	}

	@Override
	public String toString() {
		return "NotificationsDto [id=" + id + ", message=" + message + ", notificationTime=" + notificationTime
				+ ", carId=" + carId + ", userId=" + userId + ", skillId=" + skillId + "]";
	}
}
