package com.smartcarservice.ua.SmartCarService.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.smartcarservice.ua.SmartCarService.service.FaultCodeService;

@RestController
public class AppointmentController {

	FaultCodeService faultCodeService;
	
	@Autowired
	public AppointmentController(FaultCodeService faultCodeService) {
		this.faultCodeService = faultCodeService;
	}
	
	@RequestMapping ("/appointment")
	public HashMap<String, Object> getAdditionalAppointments (@RequestParam(value="userId") Long userId,
										   @RequestParam(value="carId") Long carId,
										   @RequestParam(value="skillId") Long skillId) {
		HashMap<String, Object> toReturn = new HashMap<>();
		try {
			Set<Long> skillIdsSet = new HashSet<>();
			skillIdsSet.add(skillId);
			//sensors alerts
			
			toReturn.put("skillIdsSet", skillIdsSet);
			toReturn.put("carId", carId);
		} catch (Exception e) {
			System.out.println("Appointment exception: " + e.getMessage());
		}
		return toReturn;
	}
}
