package com.smartcarservice.ua.SmartCarService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.NotificationsDto;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.Notifications;
import com.smartcarservice.ua.SmartCarService.service.NotificationService;

@RestController
public class NotificationsController {

	@Autowired
	private NotificationService notificationsService;
	
	@RequestMapping ("/notifications")
	public List<NotificationsDto> getNotifications 
	(@RequestParam(value = "userId", defaultValue = "1")Long userId){
		return notificationsService.getAllNotificationsForUser(userId);
	}
		
}
