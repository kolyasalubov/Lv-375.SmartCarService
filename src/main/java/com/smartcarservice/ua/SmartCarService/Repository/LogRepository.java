package com.smartcarservice.ua.SmartCarService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.smartcarservice.ua.SmartCarService.entity.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity, Long> {

	
	LogEntity findByUserName(String userName);
	
	
}
