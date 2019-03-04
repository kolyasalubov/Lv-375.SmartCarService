package ua.ita.smartcarservice.service.alerts;

import java.util.List;
import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.alerts.Notifications;

public interface NotificationService {

	/* CREATE */
	void saveNotification(NotificationsDto notificationDto);

	void saveAllNotifications(List<NotificationsDto> notificationsList);

	void addNewNotification(RecordDto recordDto);

	/* READ */
	List<NotificationsDto> getAllNotificationsForUser(Long userId);

	NotificationsDto findLastNotificationByMessage(String message);

	NotificationsDto getNotification (Long id);

	/* UPDATE */

	void disableNotification(NotificationsDto notificationDto);

	/* DELETE */
	void deleteNotificationById (Long id);

}
