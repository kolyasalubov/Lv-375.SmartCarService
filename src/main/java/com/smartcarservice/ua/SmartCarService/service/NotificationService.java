package com.smartcarservice.ua.SmartCarService.service;

import java.util.List;
import com.smartcarservice.ua.SmartCarService.dto.stoDto.NotificationsDto;

public interface NotificationService {
	
	void saveNotification(NotificationsDto notificationDto);

	void saveAllNotifications(List<NotificationsDto> notificationsList);
	
	List<NotificationsDto> getAllNotificationsForUser(Long userId);
	
	void deleteNotificationById (Long id);
}
