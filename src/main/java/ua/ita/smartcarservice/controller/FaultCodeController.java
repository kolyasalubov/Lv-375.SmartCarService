package ua.ita.smartcarservice.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.ita.smartcarservice.dto.stoDto.FaultCodeDto;
import ua.ita.smartcarservice.service.FaultCodeService;


@RestController
public class FaultCodeController {
	
	FaultCodeService faultCodeService;
	
	@Autowired
	public FaultCodeController(FaultCodeService faultCodeService) {
		this.faultCodeService = faultCodeService;
	}

	@RequestMapping ("/faultCode")
	public HashMap<String, Object> getFaultCode(@RequestParam(value="vinNumber") String vinNumber, 
							 @RequestParam(value="code") String code) {
		HashMap<String, Object> toReturn = new HashMap<>();
		try {
			FaultCodeDto inputFC = faultCodeService.getFaultCode(code);
			//CarDto inputCar = carService.getCarByVinNum(vinNumber)
			toReturn.put("faultCode", inputFC);
			//toReturn.put("car", inputCar);
		} catch (Exception e) {
			System.out.println("Fault code exception: " + e.getMessage());
		}
		return toReturn;
	}
	
}
