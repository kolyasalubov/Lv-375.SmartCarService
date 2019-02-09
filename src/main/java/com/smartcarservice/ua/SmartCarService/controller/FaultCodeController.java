package com.smartcarservice.ua.SmartCarService.controller;

import java.sql.Timestamp;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.CarDto;
import com.smartcarservice.ua.SmartCarService.dto.stoDto.FaultCodeDto;
import com.smartcarservice.ua.SmartCarService.dto.stoDto.NotificationsDto;
import com.smartcarservice.ua.SmartCarService.service.CarService;
import com.smartcarservice.ua.SmartCarService.service.FaultCodeService;
import com.smartcarservice.ua.SmartCarService.service.NotificationService;


@RestController
public class FaultCodeController {
	
	FaultCodeService faultCodeService;
	CarService carService;
	NotificationService notificationsService;
	
	@Autowired
	public FaultCodeController(FaultCodeService faultCodeService,
							   CarService carService,
							   NotificationService notificationsService) {
		this.faultCodeService = faultCodeService;
		this.carService = carService;
		this.notificationsService = notificationsService;
	}

	@RequestMapping ("/faultCode")
	public void handleFaultCode(@RequestParam(value="vinNumber") String vinNumber, 
								@RequestParam(value="code") String code) {
		try {
			FaultCodeDto faultCode = faultCodeService.getFaultCode(code);
			CarDto car = carService.findByVin(vinNumber);
			NotificationsDto toSave = new NotificationsDto(
					faultCode.getDescription(), 
					new Timestamp(System.currentTimeMillis()), 
					car.getId(), 
					car.getCarOwner().getId(), 
					faultCode.getSkill().getSkillId()
			);
			notificationsService.saveNotification(toSave);
		} catch (Exception e) {
			System.out.println("ojojoj FaultCode: " + e.getMessage());
		}
	}
	
}
