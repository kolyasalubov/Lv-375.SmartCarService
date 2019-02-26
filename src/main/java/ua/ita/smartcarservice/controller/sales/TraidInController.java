package ua.ita.smartcarservice.controller.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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


    @PostMapping(path = "api/traidin/{username}/create")
    public ResponseEntity<?> createTradeIn(CreateTraidInDto traidInDto){

tradeInService.createTradeIn(traidInDto);
        return new ResponseEntity<Void> (HttpStatus.OK);

    }

//    @GetMapping("api/tradeIn/{username}")


//TODO
    // find all tradein by salemanager's dealer using username salemanager
//    @GetMapping(path = "api/traidin/getallbymydieler")

}
