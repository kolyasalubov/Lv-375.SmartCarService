package com.smartcarservice.ua.SmartCarService.Controller.sales;

import com.smartcarservice.ua.SmartCarService.Service.SalesManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesManagerController {
    SalesManagerService salesManagerService;
//    @RequestMapping("/")
//    private String toMainPage(Model model){
 //       model.addAttribute("smAll",salesManagerService.findAll());
//        return "";
//    }
    @RequestMapping ("/salesManagerInfo")
    public void getSalesManagerInfo () {

    }
}
