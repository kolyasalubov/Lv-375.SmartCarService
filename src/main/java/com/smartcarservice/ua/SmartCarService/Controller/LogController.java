package com.smartcarservice.ua.SmartCarService.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcarservice.ua.SmartCarService.Repository.LogRepository;
import com.smartcarservice.ua.SmartCarService.entity.LogEntity;

@RestController
@RequestMapping("/user")
public class LogController {
	
	private LogRepository logRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	public LogController(LogRepository logRepository, BCryptPasswordEncoder bCryptPasswordEncoder ) {
		this.logRepository = logRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		
	}
	
	@PostMapping("/signup")
	public void signUp(@RequestBody LogEntity logEntity) {

		System.out.println(logEntity);
		
		logEntity.setPassword(bCryptPasswordEncoder.encode(logEntity.getPassword()));
		logRepository.save(logEntity);
		
		
	}
	
	

}
