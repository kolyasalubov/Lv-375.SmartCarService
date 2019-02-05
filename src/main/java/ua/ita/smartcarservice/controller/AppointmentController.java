package ua.ita.smartcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.service.FaultCodeService;

@RestController
public class AppointmentController {

	FaultCodeService faultCodeService;
	
	@Autowired
	public AppointmentController(FaultCodeService faultCodeService) {
		this.faultCodeService = faultCodeService;
	}
	
	@RequestMapping ("/appointment")
	public void getAdditionalAppointments (@RequestParam(value="userId") Long userId,
										   @RequestParam(value="carId") Long carId,
										   @RequestParam(value="skillId") int skillId) {
		
	}
}
