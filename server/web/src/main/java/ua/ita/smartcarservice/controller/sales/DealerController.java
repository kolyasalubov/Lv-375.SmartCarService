package ua.ita.smartcarservice.controller.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.sales.DealerCarDto;
import ua.ita.smartcarservice.dto.sales.DealerDto;
import ua.ita.smartcarservice.dto.sales.DealerStoAddDto;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.service.CarService;
import ua.ita.smartcarservice.service.UserService;
import ua.ita.smartcarservice.service.sales.DealerService;
import ua.ita.smartcarservice.service.sales.TradeInService;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;

import java.util.List;

/**
 * Created by 1 on 27.02.2019.
 */
//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(value = "http://localhost:9501/api/dealer/")
@RestController
public class DealerController {


    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    DealerService dealerService;

    @Autowired
    TechnicalServiceService technicalServiceService;

    @Autowired
    TradeInService tradeInService;

    @GetMapping(path = "api/dealer/getAll")
    public ResponseEntity<List<DealerDto>> getAllDealers(){
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getAllDealerDto());
    }


    @GetMapping(path = "api/dealer/get/{username}")
    public ResponseEntity<DealerDto> getDealerDto(@PathVariable String username) {

        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getDealerDtoByUserName(username));

    }


    @PostMapping(path = "api/dealer/edit/{username}")
    public ResponseEntity<?> updateDealer(@PathVariable String username, DealerDto dealerDto) {

        System.out.println("dealerDto " + dealerDto);
        dealerService.editDealerByDealerDto(dealerDto);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    @PutMapping(path = "api/dealer/create")
    public ResponseEntity<?> createDealer(@RequestParam(value = "dealerName") String dealerName,
                                          @RequestParam(value = "dealerAddress") String dealerAddress,
                                          @RequestParam(value = "dealerEdr") String dealerEdr,
                                          @RequestParam(value = "dealerEmail") String dealerEmail,
                                          @RequestParam(value = "username") String username

    ) {
        System.out.println(dealerName);
        System.out.println(dealerAddress);
        System.out.println(username);
        DealerEntity dealerEntity = new DealerEntity(userService.findByUserName(username), dealerName, dealerAddress, dealerEdr, dealerEmail);
        dealerService.createDealer(dealerEntity);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    @GetMapping(path = "api/dealer/getAllDealer")
    public ResponseEntity<List<DealerDto>> getAllDealerDto() {

        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getAllDealerDto());

    }


//    @PostMapping(path = "api/dealer/createcar/{username}")
//    public ResponseEntity<?>createDealerCar(@PathVariable String username, CarDto carDto){
//        System.out.println("createDealerCar");
//        System.out.println(username);
//        System.out.println(carDto);
////        carService.createByDealer(dealerCarDto,username);
//        return new ResponseEntity<Void> (HttpStatus.OK);
//
//    }


    @GetMapping(path = "api/dealer/{username}/getAllCar")
    public ResponseEntity<List<DealerCarDto>> getAllDealerCar(@PathVariable String username) {

        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok().headers(responseHeaders).body(dealerService.getAllCarDtoByUserNameDealer(username));
    }

    @PostMapping(path = "api/dealer/{username}/createSto")
    public ResponseEntity<?> createDealerSto(@PathVariable String username, DealerStoAddDto stoAddDto) {
        technicalServiceService.createTechnicalServiceByDealer(stoAddDto, username);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("api/dealer/createcar")
    public ResponseEntity createDealerCar(@RequestParam(value = "brand") String brand,
                                          @RequestParam(value = "model") String model,
                                          @RequestParam(value = "graduationyear") String graduation_year,
                                          @RequestParam(value = "number") String number,
                                          @RequestParam(value = "price") Double price,
                                          @RequestParam(value = "vin") String vin,
                                          @RequestParam(value = "username") String username) {

        System.out.println(brand);
        System.out.println(model);
        System.out.println(graduation_year);
        System.out.println(number);
        System.out.println(price);
        System.out.println(vin);
        System.out.println(username);

        carService.createByDealer(brand, model, graduation_year, number,price, vin, username);
//     carService.create(brand, model, graduation_year, number, vin, username);
        return new ResponseEntity(HttpStatus.CREATED);


    }


    @PutMapping("api/dealer/createtechservices")
    public ResponseEntity createTechnicalService(@RequestParam(value="username") String username,
                                                 @RequestParam(value = "name") String name,
                                                 @RequestParam(value = "address") String address) {

        System.out.println(username);
        System.out.println(name);
        System.out.println(address);


        technicalServiceService.createTechnicalServiceByDealer(new DealerStoAddDto(name,address),username);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    /* Get dealer's cars */
    @GetMapping("api/dealer/cars/{username}")
    public ResponseEntity<List<CarDto>> findByDealerUsername(@PathVariable String username) {
        List<CarDto> cars = carService.findbyUserLogin(username);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    @GetMapping("api/dealer/getAllCars/{edr}")
    public ResponseEntity<List<CarDto>> findByDealerEdr(@PathVariable String edr) {

        List<CarDto> cars = carService.findByDealerEdr(edr);

        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("api/dealer/allstos/{username}")
    public ResponseEntity<List<TechnicalServiceDto>> findAllStoByDealer(@PathVariable String username){
        System.out.println(username);
        List<TechnicalServiceDto>technicalServiceDtos=technicalServiceService.getAllTechnicalServicesDtoByDealer(username);
        if (technicalServiceDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(technicalServiceDtos, HttpStatus.OK);
    }


    @GetMapping("api/dealer/allCars")
    public  ResponseEntity<List<CarDto>> getAllDealersCars(){
        List<CarDto>cars=carService.findAllDealersCars();


        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("api/dealer/createTradeIn")
    public ResponseEntity createTradeIn(@RequestParam(value="vinNewCar") String vinNewCar,
                                                 @RequestParam(value = "vinBCar") String vinBCar
                                                 ) {

        System.out.println(vinNewCar);
        System.out.println(vinBCar);

        tradeInService.create(vinNewCar,vinBCar);


        return new ResponseEntity(HttpStatus.CREATED);

    }

}
