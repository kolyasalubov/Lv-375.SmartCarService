package com.smartcarservice.ua.SmartCarService.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.NotificationsDto;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.Notifications;
import com.smartcarservice.ua.SmartCarService.service.NotificationService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api/notifications"})
public class NotificationsController {

	@Autowired
	private NotificationService notificationsService;
	
	@GetMapping
	public List<NotificationsDto> getNotifications 
	(@RequestParam(value = "userId", defaultValue = "1")Long userId){
		return notificationsService.getAllNotificationsForUser(userId);
	}
	
	@DeleteMapping(path ={"/{id}"})
	 public void delete(@PathVariable("id") Long id) {
		notificationsService.deleteNotificationById(id);
	 }
		
}
