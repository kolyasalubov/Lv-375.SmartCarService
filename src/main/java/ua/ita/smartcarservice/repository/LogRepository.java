package ua.ita.smartcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.ita.smartcarservice.entity.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity, Long> {

		LogEntity findByUserName(String userName);
	
	}
