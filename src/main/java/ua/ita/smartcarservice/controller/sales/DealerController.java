package ua.ita.smartcarservice.controller.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.sales.DealerCarDto;
import ua.ita.smartcarservice.dto.sales.DealerDto;
import ua.ita.smartcarservice.service.CarService;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.sales.DealerService;

import java.util.List;

/**
 * Created by 1 on 16.02.2019.
 */
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(value = "http://localhost:9501/api/dealer/")
@RestController
public class DealerController {



    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

@Autowired
    DealerService dealerService;

    @GetMapping(path = "api/dealer/get/{username}")
    public ResponseEntity<DealerDto> getDealerDto(@PathVariable String username ){
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getDealerDtoByUserName(username));

//        return dealerService.getDealerDtoByUserName(username);
    }

    @PostMapping(path = "api/dealer/edit/{username}")
    public ResponseEntity<?>updateDealer(@PathVariable String username,DealerDto dealerDto ){
        System.out.println("dealerDto "+dealerDto);
        dealerService.editDealerByDealerDto(dealerDto);
return new ResponseEntity<Void> (HttpStatus.OK);
    }


    @PostMapping(path = "api/dealer/create/{username}")
    public ResponseEntity<?>createDealer(@PathVariable String username,DealerDto dealerDto ){
        dealerService.createDealer(dealerService.dealerDtoToEntity(dealerDto),username);
        return new ResponseEntity<Void> (HttpStatus.OK);
    }


    @GetMapping(path = "api/dealer/getAllDealer")
    public ResponseEntity<List<DealerDto>>getAllDealerDto(){
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getAllDealerDto());
    }


    @PostMapping(path = "api/dealer/{username}/createcar")
    public ResponseEntity<?>createDealerCar(@PathVariable String username,DealerCarDto dealerCarDto ){
        carService.createByDealer(dealerCarDto,username);
        return new ResponseEntity<Void> (HttpStatus.OK);
    }


    @GetMapping(path = "api/dealer/{username}/getAllCar")
    public ResponseEntity<List<DealerCarDto>>getAllDealerCar(@PathVariable String username){
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getAllCarDtoByUserNameDealer(username));
    }



}
