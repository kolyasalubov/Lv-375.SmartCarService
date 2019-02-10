package ua.ita.smartcarservice.controller.technicalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.util.List;

/**
 * Controller (REST) works with TechnicalService
 */
@RestController
public class TechnicalServiceController {

    @Autowired
    TechnicalServiceService technicalServiceService;

    @Autowired
    UserService userService;

    @Autowired
    WorkerService workerService;



    /*
    Method for getting all the technical services
     */
    @GetMapping("/api/v1/techservices")
    ResponseEntity<List<TechnicalServiceDto>> getAllTechnicalServices() {
        ResponseEntity<List<TechnicalServiceDto>> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(technicalServiceService.getAllTechnicalServicesDto(), HttpStatus.OK);
        } catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    /*
    Method for creating TechnicalService, it gets parameters from the URL
     */
    @PostMapping("/api/v1/users/{id}/techservices")
    @ResponseBody
    void createTechnicalService(
            @PathVariable Long id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "address") String address) {

        technicalServiceService.createTechnicalService(name, address, id);
    }

    /*
    Method for getting whole information about about current TechnicalService by its id
     */
    @GetMapping("/api/v1/techservices/{id}")
    ResponseEntity<TechnicalServiceDto> getTechnicalService(@PathVariable Long id) {
        ResponseEntity<TechnicalServiceDto> responseEntity;

        try{
            responseEntity = new ResponseEntity<>(
                    technicalServiceService.getTechnicalServiceDtoById(id), HttpStatus.OK);
        } catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        return responseEntity;
    }

    /*
    Method for getting Workers from current TechnicalService,
    it gets parameters from the URL
     */
    @GetMapping("/api/v1/techservices/{id}/workers")
    ResponseEntity<List<UserEntity>> getTechnicalServiceWorkers(@PathVariable Long id) {
        ResponseEntity<List<UserEntity>> responseEntity;

        try{
            responseEntity = new ResponseEntity<List<UserEntity>>(
                    workerService.getWorkersByTechnicalServiceId(
                            technicalServiceService.getTechnicalServiceById(id)),
                    HttpStatus.OK);
        } catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    /*
    Method for updating information about current TechnicalService,
    it gets parameters from the URL
     */
    @PutMapping("/api/v1/techservices/{id}")
    void updateTechnicalService(@PathVariable Long id,
                                               @RequestParam(value = "name", required = false) String name,
                                               @RequestParam(value = "address", required = false) String address) {

        TechnicalServiceEntity technicalServiceEntity = technicalServiceService.getTechnicalServiceById(id);

        if (name != null) {
            technicalServiceEntity.setName(name);
        }

        if (address != null) {
            technicalServiceEntity.setAddress(address);
        }

        technicalServiceService.updateTechnicalService(technicalServiceEntity);
    }

    /*
   Method for deleting Worker by id from DB and current TechnicalManager`s worker list
    */
    @DeleteMapping("/api/v1/workers/{workerId}")
    void deleteWorker(@PathVariable Long workerId) throws Exception {
        userService.deleteById(workerId);
    }

    @PostMapping("/api/v1/techservices/{id}/workers/{workerId}")
    @ResponseBody
    void addWorkerToSerice(@PathVariable Long id, @PathVariable Long workerId){
        //TODO
    }
/*
    @PostMapping("/addtechservicesToDealer")
    @ResponseBody
    void createTechnicalServiceByDealer(@RequestParam(value = "name") String name,
                                        @RequestParam(value = "address") String address,
                                        @RequestParam(value = "DealerId") Long id) {

        technicalServiceService.createTechnicalServiceByDealer(name, address, id);

    }*/
}
