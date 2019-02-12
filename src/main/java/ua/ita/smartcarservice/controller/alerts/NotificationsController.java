package ua.ita.smartcarservice.controller.alerts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.service.alerts.NotificationService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api/notifications"})
public class NotificationsController {

	@Autowired
	private NotificationService notificationsService;
	
	@GetMapping(path ={"/{id}"})
	public List<NotificationsDto> getNotifications(@PathVariable("id") Long id){
		return notificationsService.getAllNotificationsForUser(id);
	}
	
	@DeleteMapping(path ={"/{id}"})
	 public void delete(@PathVariable("id") Long id) {
		notificationsService.deleteNotificationById(id);
	 }		
}
