package com.smartcarservice.ua.SmartCarService.controller;

import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.service.TechnicalManagerService;
import com.smartcarservice.ua.SmartCarService.serviceImpl.TechnicalManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TechnicalManagerController {

    @Autowired
    TechnicalManagerService service;

    @GetMapping("/techmanagers/")
    TechnicalManager getAllTechnicalManagers() {
        return null;
    }

    @GetMapping("/techmanagers/{id}")
    TechnicalManager getTechnicalManager(@PathVariable Long id) {
        return service.getTechnicalManager(id);
    }
}
