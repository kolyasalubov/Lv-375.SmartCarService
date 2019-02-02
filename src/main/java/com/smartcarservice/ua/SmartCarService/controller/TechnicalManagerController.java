package com.smartcarservice.ua.SmartCarService.controller;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.TechnicalManagerDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalManager;
import com.smartcarservice.ua.SmartCarService.service.TechnicalManagerService;
import com.smartcarservice.ua.SmartCarService.serviceImpl.TechnicalManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TechnicalManagerController {

    @Autowired
    TechnicalManagerService service;

    @GetMapping("/techmanagers/")
    TechnicalManager getAllTechnicalManagers() {
        return null;
    }

    @GetMapping("/techmanagers/{id}")
    @ResponseBody TechnicalManagerDto getTechnicalManager(@PathVariable Long id) {
        //ResponseEntity<TechnicalManagerDto> responseEntity;
        return service.getTechnicalManager(id);
    }
}
