package com.smartcarservice.ua.SmartCarService.controller;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.TechnicalServiceDto;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import com.smartcarservice.ua.SmartCarService.service.TechnicalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TechnicalServiceController {

    @Autowired
    TechnicalServiceService technicalServiceService;

    @GetMapping("/techservices")
    List<TechnicalService> getAllTechnicalServices(){
        return technicalServiceService.getAllTechnicalServices();
    }

    @PostMapping("/techservices/create")
    @ResponseBody
    void createTechnicalService(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "address") String address){

        technicalServiceService.createTechnicalService(name, address);
    }
}
