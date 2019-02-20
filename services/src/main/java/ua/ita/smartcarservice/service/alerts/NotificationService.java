package ua.ita.smartcarservice.service.alerts;

import java.util.List;
import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.entity.alerts.Notifications;

public interface NotificationService {
	
	void saveNotification(NotificationsDto notificationDto);

	void saveAllNotifications(List<NotificationsDto> notificationsList);
	
	List<NotificationsDto> getAllNotificationsForUser(Long userId);
	
	void deleteNotificationById (Long id);
	
	NotificationsDto findLastNotificationByMessage(String message);
}
