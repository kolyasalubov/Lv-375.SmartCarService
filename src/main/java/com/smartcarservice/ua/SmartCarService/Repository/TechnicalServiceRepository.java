package com.smartcarservice.ua.SmartCarService.repository;

import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalServiceRepository extends JpaRepository<TechnicalService, Long> {
}
