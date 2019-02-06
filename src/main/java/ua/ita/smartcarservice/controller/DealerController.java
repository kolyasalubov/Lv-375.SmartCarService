package ua.ita.smartcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.stoDto.CarDto;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.service.CarService;
import ua.ita.smartcarservice.service.DealerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class DealerController {

    @Autowired
    DealerService dealerService;

    @Autowired
    CarService carService;




    @PostMapping("/createDealer")
    public void createDealer(@RequestParam(value = "email")String email,
                             @RequestParam(value = "password")String password,
                             @RequestParam(value = "fullname")String fullname,
                             @RequestParam(value ="userName" )String username,
                             @RequestParam(value = "phoneNumber")String phone,
                             @RequestParam(value = "adress")String adress,
                             @RequestParam(value = "edr")String edr){

        Dealer dealer=new Dealer(email,password,fullname,username,phone,adress,edr);
        dealerService.CreateDealer(dealer);

    }


    @PostMapping("/setguaranteeToCar")
     public void  setToCarEnd_guarantee(@RequestParam(value = "vin")String vin,
                                        @RequestParam(value = "end_guarantee")String date
                                        ){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        Date parsingDate = null;
        try {
            parsingDate = ft.parse(date);
        }catch (ParseException e) {
            System.out.println("error with parsing ");
        }


        Car car=carService.findCarByVin(vin);
        car.setEnd_guarantee(parsingDate);

        carService.updateCar(car);



    }



    @DeleteMapping("/deleteDealer")
    public void deleteDealer(@RequestParam(value = "id")Long id){

        List<Car> cars=carService.dealerCars(id);
for (int i=0;i<cars.size();i++){
    cars.get(i).setDealer(null);
}

        dealerService.DeleteDealer(dealerService.findById(id));

    }

    @GetMapping("/dealers/{id}")
    @ResponseBody
    Dealer getdealer(@PathVariable Long id){
        return dealerService.findById(id);
    }


    @GetMapping("/dealers")
    List<Dealer> getAllDealler(){

        return dealerService.findAll();
    }







}
