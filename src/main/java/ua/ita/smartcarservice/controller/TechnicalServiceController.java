package ua.ita.smartcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.service.impl.DealerServiceImpl;
import ua.ita.smartcarservice.dto.stoDto.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
import ua.ita.smartcarservice.service.TechnicalServiceService;

import java.util.List;

/**
 * Controller (REST) works with TechnicalService
 */
@RestController
public class TechnicalServiceController {

    @Autowired
    TechnicalServiceService technicalServiceService;

    @Autowired
    DealerServiceImpl dealerService;

    /*
    Method for getting all the technical services
     */
    @GetMapping("/api/v1/techservices")
    List<TechnicalServiceDto> getAllTechnicalServices() {
        return technicalServiceService.getAllTechnicalServicesDto();
    }

    /*
    Method for creating TechnicalService, it gets parameters from the URL
     */
    @PostMapping("/api/v1/techservices")
    @ResponseBody
    void createTechnicalService(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "address") String address) {

        technicalServiceService.createTechnicalService(name, address);
    }

    /*
    Method for getting whole information about about current TechnicalService by its id
     */
    @GetMapping("/api/v1/techservices/{id}")
    TechnicalServiceDto getTechnicalService(@PathVariable Long id) {
        return technicalServiceService.getTechnicalServiceDtoById(id);
    }

    /*
    Method for updating information about current TechnicalService,
    it gets parameters from the URL
     */
    @PutMapping("/api/v1/techservices/{id}")
    TechnicalServiceDto updateTechnicalService(@PathVariable Long id,
                                               @RequestParam(value = "name", required = false) String name,
                                               @RequestParam(value = "address", required = false) String address) {

        TechnicalService technicalService = technicalServiceService.getTechnicalServiceById(id);

        if (name != null) {
            technicalService.setName(name);
        }

        if (address != null) {
            technicalService.setAddress(address);
        }

        return technicalServiceService.updateTechnicalService(technicalService);
    }

    @PostMapping("/addtechservicesToDealer")
    @ResponseBody
    void createTechnicalServiceByDealer(@RequestParam(value = "name") String name,
                                        @RequestParam(value = "address") String address,
                                        @RequestParam(value = "DealerId") Long id) {

        technicalServiceService.createTechnicalServiceByDealer(name, address, id);

    }
}
