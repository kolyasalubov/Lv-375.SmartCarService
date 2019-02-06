package ua.ita.smartcarservice.controller;

import ua.ita.smartcarservice.dto.stoDto.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
import ua.ita.smartcarservice.service.TechnicalServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.service.impl.DealerServiceImpl;

import java.util.List;

@RestController
public class TechnicalServiceController {

    @Autowired
    TechnicalServiceService technicalServiceService;


    @Autowired
    DealerServiceImpl dealerService;

    @GetMapping("/api/techservices")
    List<TechnicalServiceDto> getAllTechnicalServices(){
        return technicalServiceService.getAllTechnicalServicesDto();
    }


    @PostMapping("/api/techservices")
    @ResponseBody
    void createTechnicalService(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "address") String address){

        technicalServiceService.createTechnicalService(name, address);
    }

    @GetMapping("/api/techservices/{id}")
    TechnicalServiceDto getTechnicalService(@PathVariable Long id){
        return technicalServiceService.getTechnicalServiceDtoById(id);
    }

    @PutMapping("/api/techservices/{id}")
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

    @PostMapping("/addtechservicesToDealer")
    @ResponseBody
    void createTechnicalServiceByDealer( @RequestParam(value = "name") String name,
                                         @RequestParam(value = "address") String address,
                                         @RequestParam(value = "DealerId")Long id){

        technicalServiceService.createTechnicalServiceByDealer(name,address,id);

    }
}
