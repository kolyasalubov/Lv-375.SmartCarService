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
    List<TechnicalServiceDto> getAllTechnicalServices(){
        return technicalServiceService.getAllTechnicalServicesDto();
    }

    @PostMapping("/techservices")
    @ResponseBody
    void createTechnicalService(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "address") String address){

        technicalServiceService.createTechnicalService(name, address);
    }

    @GetMapping("/techservices/{id}")
    TechnicalServiceDto getTechnicalService(@PathVariable Long id){
        return technicalServiceService.getTechnicalServiceDtoById(id);
    }

    @PutMapping("/techservices/{id}")
    TechnicalServiceDto updateTechnicalService(@PathVariable Long id,
                                               @RequestParam(value = "name", required = false) String name,
                                               @RequestParam(value = "address", required = false) String address){

        TechnicalService technicalService = technicalServiceService.getTechnicalServiceById(id);

        if(name != null){
            technicalService.setName(name);
        }

        if(address != null){
            technicalService.setAddress(address);
        }

        return technicalServiceService.updateTechnicalService(technicalService);
    }
}
