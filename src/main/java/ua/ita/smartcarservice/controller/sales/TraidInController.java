package ua.ita.smartcarservice.controller.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.sales.CreateTraidInDto;
import ua.ita.smartcarservice.service.sales.DealerService;
import ua.ita.smartcarservice.service.sales.TradeInService;

/**
 * Created by 1 on 17.02.2019.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TraidInController {

    @Autowired
    DealerService dealerService;

    @Autowired
    TradeInService tradeInService;

//    @PostMapping(path = "api/dealer/{username}/createcar")
//    public ResponseEntity<?>createDealerCar(@PathVariable String username,DealerCarDto dealerCarDto ){
//
//        carService.createByDealer(dealerCarDto,username);
//        return new ResponseEntity<Void> (HttpStatus.OK);
//
//    }



    @PostMapping(path = "api/traidin/{username}/create")
    public ResponseEntity<?> createTradeIn(CreateTraidInDto traidInDto){

//tradeInService.
        return new ResponseEntity<Void> (HttpStatus.OK);

    }

}
