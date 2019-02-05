package com.smartcarservice.ua.SmartCarService.repository;

import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnicalManagerRepository extends JpaRepository<TechnicalManager, Long> {

    TechnicalManager getTechnicalManagerById(Long id);
}
