package com.cjhrxS.ua.sec.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cjhrxS.ua.sec.Entity.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity, Long> {

	
	LogEntity findByUserName(String userName);
	
	
}
