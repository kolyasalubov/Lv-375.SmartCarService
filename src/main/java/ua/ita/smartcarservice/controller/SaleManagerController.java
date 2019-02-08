package ua.ita.smartcarservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.entity.sales.SalesManager;
import ua.ita.smartcarservice.service.DealerService;
import ua.ita.smartcarservice.service.SalesManagerService;

/**
 * Created by 1 on 05.02.2019.
 */

@RestController
public class SaleManagerController {

    @Autowired
    SalesManagerService salesManagerService;
    @Autowired
    DealerService dealerService;



    @PostMapping("/createSaleManager")
    public void createSalemanager(@RequestParam(value = "email")String email,
                                  @RequestParam(value = "password")String password,
                                  @RequestParam(value = "fullname")String fullname,
                                  @RequestParam(value ="userName" )String username,
                                  @RequestParam(value = "dealerid")Long id){

        SalesManager salesManager=new SalesManager(email,password,fullname,username,dealerService.findById(id));


        salesManagerService.createSaleManager(salesManager);

    }




}
