package ua.ita.smartcarservice.service.impl.alerts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.ita.smartcarservice.dto.alerts.NotificationsDto;
import ua.ita.smartcarservice.entity.alerts.Notifications;
import ua.ita.smartcarservice.repository.alerts.NotificationsRepository;
import ua.ita.smartcarservice.service.alerts.NotificationService;

@Service
public class NotificationsServiceImpl implements NotificationService{

	@Autowired
	private NotificationsRepository notificationsRepository;

	/* CREATE */
	@Override
	public void saveNotification(NotificationsDto notificationDto) {
		notificationsRepository.save(dtoToEntity(notificationDto));
	}

	@Override
	public void saveAllNotifications(List<NotificationsDto> notificationsList) {
		List<Notifications> entities = new ArrayList<>();
		for (NotificationsDto dto : notificationsList) {
			entities.add(dtoToEntity(dto));
		}
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
		for (Notifications entity : notificationsRepository.getAllNotificationsForUser(userId)) {
			dtos.add(entityToDto(entity));
		}
		return dtos;
	}

	@Override
	public NotificationsDto findLastNotificationByMessage(String message) {
		return entityToDto(notificationsRepository.findLastNotificationByMessage(message));
	}

	/* UPDATE */
	@Override
	public void updateNotification(NotificationsDto notificationDto) {
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
				dto.getSkillId()) ;
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
				entity.getSkillId());
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
				dto.getSkillId()) ;
	}
}
